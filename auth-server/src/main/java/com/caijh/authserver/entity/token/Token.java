/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-12-6 下午10:39
 *  @createTime: 2018-12-06 22:39:25
 *  @classPath: com.caijh.authserver.entity.token.Token
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.entity.token;

import lombok.Builder;
import lombok.Data;

/**
 * @author cjh
 * token实体对象
 */
@Data
@Builder
public class Token {
    /**
     * token是否有效
     */
    private boolean isValid;
    /**
     * 是否允许重新获取token
     */
    private boolean canRefresh;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 头部
     */
    private JwtHead head;
    /**
     * 有效载荷【记录token有效信息】
     */
    private JwtBody body;
    /**
     * 签名
     */
    private String signature;

}
