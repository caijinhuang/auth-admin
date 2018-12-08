/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-12-6 下午10:40
 *  @createTime: 2018-12-06 22:40:26
 *  @classPath: com.caijh.authserver.entity.token.JwtHead
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.entity.token;

import lombok.Builder;
import lombok.Data;

/**
 * @author cjh
 * token头部信息
 */
@Data
@Builder
public class JwtHead {
    /**
     * 使用的加密算法
     */
    private String alg;
    /**
     * token类型
     */
    private String type;
}
