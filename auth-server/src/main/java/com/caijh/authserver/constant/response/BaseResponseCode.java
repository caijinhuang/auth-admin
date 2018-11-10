/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-10 下午4:14
 *  @createTime: 2018-11-10 16:14:03
 *  @classPath: com.caijh.authserver.constant.response.BaseResponseCode
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.constant.response;

/**
 * @author cjh
 * 基本报文代码接口，
 * 继承了这个接口就能够自定义响应报文代码及其说明了
 */
public interface BaseResponseCode {
    /**
     * 获取响应代码
     * @return
     */
    public String getCode();

    /**
     * 获取代码说明
     * @return
     */
    public String getMessage();
}
