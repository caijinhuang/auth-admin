/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-4 下午9:25
 *  @createTime: 2018-11-04 21:25:51
 *  @classPath: com.caijh.authserver.web.controller.UserController
 **************************************************************************************************/

package com.caijh.authserver.web.controller;

import com.caijh.authserver.constant.message.SysHint;
import com.caijh.authserver.constant.response.ResultCode;
import com.caijh.authserver.constant.userenum.Terminal;
import com.caijh.authserver.entity.db.User;
import com.caijh.authserver.entity.query.LoginUser;
import com.caijh.authserver.entity.view.ResponseData;
import com.caijh.authserver.service.api.UserService;
import io.netty.util.internal.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    @PostMapping("/register.do")
    public ResponseData register(@RequestBody @Valid User user) {
        return userService.register(user);
    }

    @PostMapping("/login.do")
    public ResponseData login(@RequestBody @Valid User user, HttpServletRequest request) {
        String terminal = request.getHeader("TERMINAL");
        if (StringUtils.isEmpty(terminal) || !Terminal.isLegal(terminal)) {
            return ResponseData.build(null, SysHint.UNDEFINED_SOURCE, ResultCode.LOGIN_FAIL.getCode());
        }
        String token = userService.login(user, terminal);
        if (StringUtil.isNullOrEmpty(token)) {
            return ResponseData.failed(ResultCode.LOGIN_FAIL);
        }
        Map<String, String> tokenMap = new HashMap<>(1);
        tokenMap.put("token", token);
        return ResponseData.success(tokenMap);
    }

    @GetMapping("/loginOut.do")
    public ResponseData loginOut(HttpServletRequest request) {
        userService.loginOut(request);
        return ResponseData.success("已退出登陆！");
    }

}
