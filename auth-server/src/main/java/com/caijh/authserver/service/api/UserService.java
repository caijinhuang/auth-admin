/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-4 下午9:39
 *  @createTime: 2018-11-04 21:39:43
 *  @classPath: com.caijh.authserver.service.api.UserService
 **************************************************************************************************/

package com.caijh.authserver.service.api;


import com.caijh.authserver.entity.db.User;
import com.caijh.authserver.entity.view.ResponseData;

/**
 * @author cjh
 * 用户服务
 */
public interface UserService {
    /**
     * 用户注册
     * @param user 用户信息对象
     * @return 是否注册成功
     */
    ResponseData register(User user);
}
