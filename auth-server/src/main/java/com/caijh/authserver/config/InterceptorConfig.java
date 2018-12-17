/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-9-10 下午11:39
 *  @createTime: 2018-12-08 23:36:49
 *  @classPath: com.caijh.authserver.config.InterceptorConfig
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.config;

import com.caijh.authserver.web.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    AuthInterceptor authInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 过滤的请求
         */
        String[] exclude = new String[]{
                "/",
                "/img/**",
                "/js/**",
                "/css/**",
                "/config/**",
                "/page/login.html",
                "/page/register.html",

//                "/user/register",
                "/user/login.do",
                "/error",
        };
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(exclude);
    }
}
