/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-10 下午10:03
 *  @createTime: 2018-11-10 22:24:29
 *  @classPath: com.caijh.authserver.annotations.AccountType
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.annotations;

import com.caijh.authserver.constraint.AccountTypeConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author cjh
 * 账户类型依赖校验
 */
@Target({ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountTypeConstraint.class)
public @interface UserAccount {
    String message() default "{javax.validation.constraints.NotBlank.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        NotBlank[] value();
    }

}
