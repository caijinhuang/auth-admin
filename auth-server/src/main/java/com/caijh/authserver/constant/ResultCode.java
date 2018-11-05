
/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-9-2 下午10:40
 *  @createTime: 2018-11-04 21:35:40
 *  @classPath: com.caijh.authserver.constant.ResultCode
 **************************************************************************************************/

package com.caijh.authserver.constant;

/**
 * 响应结果状态码枚举类
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS("200", "成功"),

    /**
     * 操作失败
     */
    FAIL("205", "操作失败"),

    /**
     * 数据已存在
     */
    SUCCESS_IS_HAVE("208", "数据已存在"),

    /**
     * 没有结果
     */
    NOT_DATA("911", "没有结果"),


    /**
     * 没有登录
     */
    NOT_LOGIN("600", "没有登录或登陆已过期"),

    /**
     * 登陆失败
     */
    LOGIN_FAIL("601", "登陆失败，账号或密码错误"),

    /**
     * 登陆失败
     */
    REGISTER_FAIL("602", "注册失败，该用户已经存在"),
    /**
     * 发生异常
     */
    EXCEPTION("401", "发生异常"),

    /**
     * 系统错误
     */
    SYS_ERROR("402", "系统错误"),

    /**
     * 参数错误
     */
    PARAMS_ERROR("403", "参数错误 "),

    /**
     * 参数错误
     */
    NOT_FOUND("404", "哎呀，对象走丢了！ "),

    /**
     * 不支持或已经废弃
     */
    NOT_SUPPORTED("410", "不支持或已经废弃"),

    /**
     * AuthCode错误
     */
    INVALID_AUTHCODE("444", "无效的AuthCode"),

    /**
     * 太频繁的调用
     */
    TOO_FREQUENT("445", "太频繁的调用"),

    /**
     * 未知的错误
     */
    UNKNOWN_ERROR("499", "未知错误"),

    /**
     * 系统内部异常
     */
    SYS_INNER_ERROR("500", "系统内部异常"),

    /**
     * 未设置方法
     */
    NOT_METHOD("4004", "未设置方法");

    ResultCode(String value, String msg) {
        this.val = value;
        this.msg = msg;
    }

    public String val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private String val;
    private String msg;
}
