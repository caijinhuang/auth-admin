/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-11 下午1:01
 *  @createTime: 2018-11-11 19:58:22
 *  @classPath: com.caijh.authserver.web.advice.GlobalExceptionHandler
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.web.advice;

import com.caijh.authserver.constant.response.ResultCode;
import com.caijh.authserver.entity.view.ResponseData;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author cjh
 * 全局异常处理对象
 * 异常返回指定的响应数据格式
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理数据校验绑定异常
     *
     * @param e
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseData validExceptionHandler(MethodArgumentNotValidException e) {

        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        String errorMsg = objectErrors.get(0).getDefaultMessage();
        for (ObjectError error : objectErrors) {
            if (error instanceof FieldError) {
                FieldError err = (FieldError) error;
                log.error("{}:{}", err.getField(), err.getDefaultMessage());
            } else {
                log.error("对象{}:{}", error.getObjectName(), error.getDefaultMessage());
            }
        }

        return ResponseData.build(null, errorMsg, ResultCode.REGISTER_FAIL.getCode());
    }

    /**
     * 系统异常通用处理，比如：404,500
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
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return ResponseData.failed(ResultCode.NOT_FOUND);
        } else {
            String errorMsg = ResultCode.SYS_INNER_ERROR.getMessage() + ":" + e.getMessage();
            return ResponseData.build(null, errorMsg, ResultCode.SYS_INNER_ERROR.getCode());
        }
    }

}
