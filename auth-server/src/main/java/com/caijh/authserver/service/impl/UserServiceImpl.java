/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-4 下午9:39
 *  @createTime: 2018-11-04 21:39:58
 *  @classPath: com.caijh.authserver.service.impl.UserServiceImpl
 **************************************************************************************************/

package com.caijh.authserver.service.impl;

import com.caijh.authserver.dao.jpa.UserDao;
import com.caijh.authserver.entity.db.User;
import com.caijh.authserver.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author cjh
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean register(User user) {
        User resultUser = userDao.save(user);
        return true;
    }
}
