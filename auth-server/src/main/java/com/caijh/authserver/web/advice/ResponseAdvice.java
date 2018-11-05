/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-6 上午12:00
 *  @createTime: 2018-11-06 00:00:31
 *  @classPath: com.caijh.authserver.web.advice.ResponseAdvice
 **************************************************************************************************/

package com.caijh.authserver.web.advice;

import com.caijh.authserver.entity.view.ResponseData;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

/**
 * @author cjh
 * 统一的restful响应格式
 */
@Log4j2
@ControllerAdvice(basePackages = "com.caijh.authserver.web.controller")
public class ResponseAdvice extends AbstractMappingJacksonResponseBodyAdvice {
    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer,
                                           MediaType contentType,
                                           MethodParameter returnType,
                                           ServerHttpRequest request,
                                           ServerHttpResponse response) {
        Object value = bodyContainer.getValue();
        // 如果不是restful响应对象，强制包一层
        if (!(value instanceof ResponseData)) {
            bodyContainer.setValue(ResponseData.success(value));
        }
    }
}
