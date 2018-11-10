/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-9-2 下午10:16
 *  @createTime: 2018-11-04 21:35:10
 *  @classPath: com.caijh.authserver.entity.view.ResponseData
 **************************************************************************************************/

package com.caijh.authserver.entity.view;

import com.caijh.authserver.constant.response.BaseResponseCode;
import com.caijh.authserver.constant.response.ResultCode;
import lombok.Builder;
import lombok.Data;

/**
 * @param <T> 数据对象
 * @author cjh
 */
@Data
@Builder
public class ResponseData<T> {
    /**
     * 返回对象数据
     */
    private T data;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 报文状态
     */
    private String code;


    /**
     * 正常返回，构造返回值
     *
     * @param data
     */
    public ResponseData(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
        this.data = data;
    }

    /**
     * 非正常返回系统错误
     *
     * @param message
     */
    public ResponseData(String message) {
        this.code = ResultCode.SYS_ERROR.getCode();
        this.message = message;
    }

    public ResponseData(BaseResponseCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }


    public ResponseData(T data, String message, String code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    /**
     * 请求成功
     *
     * @param data 成功的反回结果
     * @param <T>  结果类型
     * @return 报文对象
     */
    public static <T> ResponseData success(T data) {
        return new ResponseData<T>(data);
    }

    /**
     * 请求失败
     *
     * @param resultCode 错误的枚举对象
     * @return 报文对象
     */
    public static <T> ResponseData failed(BaseResponseCode resultCode) {
        return new ResponseData(resultCode);
    }

    /**
     * 创建返回对象
     *
     * @param data    数据对象
     * @param message 提示信息
     * @param code    错误代码
     * @param <T>     数据类型
     * @return 报文对象
     */
    public static <T> ResponseData build(T data, String message, String code) {
        return new ResponseData<T>(data, message, code);
    }

}
