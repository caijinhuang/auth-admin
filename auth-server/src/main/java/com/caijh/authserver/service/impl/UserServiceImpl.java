/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-4 下午9:39
 *  @createTime: 2018-11-04 21:39:58
 *  @classPath: com.caijh.authserver.service.impl.UserServiceImpl
 **************************************************************************************************/

package com.caijh.authserver.service.impl;

import com.caijh.authserver.constant.response.ResultCode;
import com.caijh.authserver.dao.jpa.UserDao;
import com.caijh.authserver.entity.db.User;
import com.caijh.authserver.entity.view.ResponseData;
import com.caijh.authserver.service.api.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


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
    @Transactional(rollbackFor = Exception.class)
    public ResponseData register(User user) {

        // 新账户有效性判断
        try {
            accountValidate(user);
        } catch (Exception e) {
            return ResponseData.build(null, e.getMessage(), ResultCode.REGISTER_FAIL.getCode());
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
        List<User> userList = new ArrayList<>();

        try {
            String accountType = user.getAccountType();
            String typeUpper = toUpperCase(accountType);
            Method userMethod = user.getClass().getMethod("get" + typeUpper);
            Method daoMethod = userDao.getClass().getMethod("findUserByAccountTypeAnd" + typeUpper,
                    String.class,
                    String.class);
            String value = userMethod.invoke(user).toString();
            userList = (List<User>) daoMethod.invoke(userDao, accountType, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("用户注册反射执行失败:{}", e);
            throw new RuntimeException("注册失败");
        }

        if (!userList.isEmpty()) {
            throw new RuntimeException("注册失败：用户已经存在!");
        }
    }


    /**
     * 首字母转成大写
     *
     * @param str 要转换的字符串
     * @return 转换后的字符串
     */
    private String toUpperCase(String str) {
        char[] ch = str.toCharArray();
        ch[0] -= 32;
        return String.valueOf(ch);
    }
}
