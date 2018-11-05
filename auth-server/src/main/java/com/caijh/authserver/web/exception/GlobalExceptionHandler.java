/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-4 下午10:42
 *  @createTime: 2018-11-04 22:42:05
 *  @classPath: com.caijh.authserver.web.exception.GlobalExceptionHandler
 **************************************************************************************************/

package com.caijh.authserver.web.exception;

import com.caijh.authserver.constant.ResultCode;
import com.caijh.authserver.entity.view.ResponseData;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cjh
 * 全局异常处理对象
 * 异常返回指定的响应数据格式
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 系统异常处理，比如：404,500
     *
     * @param req
     * @param resp
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseData defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("访问出错了：", e);
        ResponseData response = null;
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            response = new ResponseData(ResultCode.NOT_FOUND);
        } else {
            response = new ResponseData(ResultCode.SYS_INNER_ERROR);
        }
        response.setData(e.getMessage());
        return response;
    }
}
