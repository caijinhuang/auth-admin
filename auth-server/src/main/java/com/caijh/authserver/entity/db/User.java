package com.caijh.authserver.entity.db;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
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
    @NotNull
    @NotBlank
    private String password;
    private String accountType;
    private String phone;
    private String email;
    private String nickname;
    private String signature;
    private String birthday;
    private String icon;
    private String status;
    private String createTime;
    private String updateTime;

}
