/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-12-23 下午9:19
 *  @createTime: 2018-12-23 21:19:38
 *  @classPath: com.caijh.authserver.entity.query.LoginUser
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.entity.query;

import com.caijh.authserver.entity.db.User;
import lombok.Data;

@Data
public class LoginUser extends User {
    /**
     * 终端标志
     */
    private String terminal;
}
