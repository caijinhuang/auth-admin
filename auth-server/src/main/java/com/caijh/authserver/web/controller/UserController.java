/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-4 下午9:25
 *  @createTime: 2018-11-04 21:25:51
 *  @classPath: com.caijh.authserver.web.controller.UserController
 **************************************************************************************************/

package com.caijh.authserver.web.controller;

import com.caijh.authserver.entity.db.User;
import com.caijh.authserver.entity.view.ResponseData;
import com.caijh.authserver.service.api.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cjh
 * 用户相关控制器
 */
@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户信息注册
     */
    @PostMapping("/register")
    public ResponseData login(@RequestBody @Valid User user) {
        return userService.register(user);
    }
}
