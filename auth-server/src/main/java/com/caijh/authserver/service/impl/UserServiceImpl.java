/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-4 下午9:39
 *  @createTime: 2018-11-04 21:39:58
 *  @classPath: com.caijh.authserver.service.impl.UserServiceImpl
 **************************************************************************************************/

package com.caijh.authserver.service.impl;

import com.caijh.authserver.constant.message.SysHint;
import com.caijh.authserver.constant.message.UserHint;
import com.caijh.authserver.constant.response.ResultCode;
import com.caijh.authserver.dao.jpa.UserDao;
import com.caijh.authserver.entity.db.User;
import com.caijh.authserver.entity.view.LoginUser;
import com.caijh.authserver.entity.view.ResponseData;
import com.caijh.authserver.service.api.UserService;
import com.caijh.authserver.utils.MapUtils;
import com.caijh.authserver.utils.StringUtils;
import com.caijh.authserver.web.token.TokenHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


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
    public String login(User user) {
        User userInfo = findUser(user, true);
        if (userInfo == null) {
            return null;
        }
        String token = TokenHelper.create(MapUtils.objectToObject(userInfo, LoginUser.class));
        return token;
    }

    @Override
    public boolean loginOut(User user) {
        return false;
    }

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
            log.error(UserHint.REGISTER_FAIL, e);
            throw new RuntimeException(UserHint.REGISTER_FAIL);
        }
        return ResponseData.success(UserHint.REGISTER_SUCCESS);
    }

    /**
     * 账户有效性验证
     *
     * @param user 注册的用户对象
     * @return 是否有效
     */
    private void accountValidate(User user) {
        User oldUser;
        try {
            oldUser = findUser(user, false);
        } catch (Exception e) {
            throw new RuntimeException(UserHint.REGISTER_FAIL);
        }

        if (oldUser != null) {
            throw new RuntimeException(UserHint.USER_EXIST);
        }
    }

    /**
     * 查询指定用户信息
     *
     * @param user
     * @return
     */
    private User findUser(User user, boolean isLogin) {
        // 如果是登陆的信息
        if (isLogin) {

        }
        User oldUser;
        try {
            String accountType = user.getAccountType();
            String typeUpper = StringUtils.toUpperCase(accountType);
            Method userMethod = user.getClass().getMethod("get" + typeUpper);
            Method daoMethod = userDao.getClass().getMethod("findUserByAccountTypeAnd" + typeUpper, String.class,
                    String.class);
            String value = userMethod.invoke(user).toString();
            oldUser = (User) daoMethod.invoke(userDao, accountType, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error(SysHint.REFLECT_FAIL, e);
            throw new RuntimeException(SysHint.SYSTEM_INNER_ERRROR);
        }

        return oldUser;
    }
}
