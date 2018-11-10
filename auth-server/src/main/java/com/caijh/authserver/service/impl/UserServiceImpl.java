/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-4 下午9:39
 *  @createTime: 2018-11-04 21:39:58
 *  @classPath: com.caijh.authserver.service.impl.UserServiceImpl
 **************************************************************************************************/

package com.caijh.authserver.service.impl;

import com.caijh.authserver.constant.response.ResultCode;
import com.caijh.authserver.constant.userenum.AccountType;
import com.caijh.authserver.dao.jpa.UserDao;
import com.caijh.authserver.entity.db.User;
import com.caijh.authserver.entity.view.ResponseData;
import com.caijh.authserver.service.api.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;


/**
 * @author cjh
 * 用户服务实现类
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public ResponseData register(User user) {
        AccountType accountType = AccountType.getType(user.getAccountType());
        if (accountType == null) {
            return ResponseData.build(null, "注册失败：账户类型错误！", ResultCode.REGISTER_FAIL.getCode());
        }

        // 账户有效性判断
        try {
            accountValidate(user);
        } catch (Exception e) {
            String errorMsg = "注册失败：" + e.getMessage();
            return ResponseData.build(null, errorMsg, ResultCode.REGISTER_FAIL.getCode());
        }

        // 用户注册
        try {
            // 保存加密后的密码
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            userDao.save(user);
        } catch (RuntimeException e) {
            log.error("注册失败了！", e);
            throw new RuntimeException("注册失败，请重试！");
        }
        return ResponseData.success("恭喜你！注册成功！");
    }

    /**
     * 账户有效性验证
     *
     * @param user 注册的用户对象
     * @return 是否有效
     */
    private void accountValidate(User user) {
        User oldUser;
        if (user.getAccountType().equals("email")) {
            if (StringUtils.isEmpty(user.getEmail())) {
                throw new RuntimeException("邮箱不能为空！");
            }
            oldUser = userDao.findUserByAccountTypeAndEmail(user.getAccountType(), user.getEmail());
        } else {
            if (StringUtils.isEmpty(user.getPhone())) {
                throw new RuntimeException("电话号码不能为空！");
            }
            oldUser = userDao.findUserByAccountTypeAndPhone(user.getAccountType(), user.getPhone());
        }

        if (oldUser != null) {
            throw new RuntimeException("用户已经存在!");
        }
    }
}
