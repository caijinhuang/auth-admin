package com.caijh.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author cjh
 * boot启动类
 */
@SpringBootApplication
@EnableCaching
public class AuthServerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AuthServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
