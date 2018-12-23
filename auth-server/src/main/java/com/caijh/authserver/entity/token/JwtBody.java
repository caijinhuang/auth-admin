/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-12-6 下午10:41
 *  @createTime: 2018-12-06 22:41:04
 *  @classPath: com.caijh.authserver.entity.token.JwtBody
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.entity.token;

import com.caijh.authserver.entity.view.UserInfo;
import lombok.Builder;
import lombok.Data;

/**
 * @author cjh
 * token的有效信息
 */
@Data
@Builder
public class JwtBody {

    /**
     * 发行人
     */
    private String issuer;

    /**
     * 有效期
     */
    private String expiryDate;

    /**
     * 签发时间
     */
    private String issueDate;

    /**
     * tokenId
     */
    private String tokenId;

    /**
     * 用户信息
     */
    private UserInfo userInfo;
}
