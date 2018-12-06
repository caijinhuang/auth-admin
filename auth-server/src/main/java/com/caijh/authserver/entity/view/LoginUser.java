/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-13 下午10:15
 *  @createTime: 2018-11-13 22:15:44
 *  @classPath: com.caijh.authserver.entity.view.LoginUser
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.entity.view;

import lombok.Data;

/**
 * @author cjh
 * 登陆用户的信息
 */
@Data
public class LoginUser {

    private String userId;
    /**
     * 账户类型
     */
    private String accountType;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮件
     */
    private String email;

    /**
     * 昵称
     */
    private String nickname;
    private String signature;
    private String birthday;
    private String icon;
    private String status;
    private String createTime;
    private String updateTime;
}
