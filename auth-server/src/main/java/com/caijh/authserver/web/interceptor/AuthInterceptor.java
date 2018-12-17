/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-12-8 下午11:37
 *  @createTime: 2018-12-08 23:37:28
 *  @classPath: com.caijh.authserver.web.interceptor.AuthInterceptor
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.web.interceptor;

import com.caijh.authserver.constant.response.ResultCode;
import com.caijh.authserver.entity.token.Token;
import com.caijh.authserver.entity.view.ResponseData;
import com.caijh.authserver.web.token.TokenHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author cjh
 * 权限控制拦截器
 */
@Log4j2
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokenStr = request.getHeader("accessToken");
        Cookie[] cookies = request.getCookies();
        if (tokenStr == null) {
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    tokenStr = cookie.getValue();
                    break;
                }
            }
        }
        if (tokenStr == null) {
            returnErrorMessage(response, ResponseData.failed(ResultCode.INVALID_AUTHCODE));
            return false;
        }
        Token token = TokenHelper.parseToken(tokenStr);
        if (!token.isValid()) {
            returnErrorMessage(response,
                    ResponseData.build(null,token.getMsg(),ResultCode.INVALID_AUTHCODE.getCode()));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 构造返回的错误信息
     *
     * @param response servlet响应对象
     */
    private void returnErrorMessage(HttpServletResponse response, ResponseData responseData) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        out.print(mapper.writeValueAsString(responseData));
        out.flush();
    }
}
