package com.caijh.authserver.entity.db;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author cjh
 * 用户对象表
 */
@Data
@Entity
@Table(name = "T_AUTH_USER")
public class User {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
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
    @Column(name="ACCOUNT_TYPE")
    private String accountType;

    /**
     * 电话号码
     */
    @Column(name="PHONE")
    private String phone;

    /**
     * 邮件
     */
    @Email(message = "不合法的邮箱地址")
    @Column(name="EMAIL")
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
