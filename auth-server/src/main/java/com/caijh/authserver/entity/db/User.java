/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-11 下午2:36
 *  @createTime: 2018-11-11 15:17:55
 *  @classPath: com.caijh.authserver.entity.db.User
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.entity.db;

import com.caijh.authserver.annotations.UserAccount;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author cjh
 * 用户对象表
 */
@Data
@Entity
@UserAccount(message = "账户类型不正确")
@Table(name = "T_AUTH_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 4386604698103035149L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String userId;

    /**
     * 密码
     */
    @NotNull
    @NotBlank
    private String password;

    /**
     * 账户类型
     */
    @NotBlank
    @NotNull
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    /**
     * 电话号码
     */
    @Pattern(regexp = "^(?:[1][3578]\\d{9})?$",message = "手机号码格式不正确")
    @Column(name = "PHONE")
    private String phone;

    /**
     * 邮件
     */
    @Email(message = "不合法的邮箱地址")
    @Column(name = "EMAIL")
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
