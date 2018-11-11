/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-10 下午10:16
 *  @createTime: 2018-11-10 22:25:19
 *  @classPath: com.caijh.authserver.constraint.AccountTypeConstraint
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.constraint;

import com.caijh.authserver.annotations.UserAccount;
import com.caijh.authserver.constant.userenum.AccountType;
import com.caijh.authserver.entity.db.User;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author cjh
 * 用户账号类型约束
 */
public class AccountTypeConstraint implements ConstraintValidator<UserAccount, Object> {

    @Override
    public void initialize(UserAccount constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        User user = (User) obj;
        AccountType accountType = AccountType.getType(user.getAccountType());
        if (accountType == null) {
            return false;
        }

        if (user.getAccountType().equals("email")) {
            if (StringUtils.isEmpty(user.getEmail())) {
                /**
                 * 删除默认提示说明
                 */
                context.disableDefaultConstraintViolation();
                /**
                 * 创建新的提示说明
                 */
                context.buildConstraintViolationWithTemplate("邮箱不能为空").addPropertyNode("email").addConstraintViolation();
                return false;
            }
        } else {
            if (StringUtils.isEmpty(user.getPhone())) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("电话号码不能为空").addPropertyNode("phone").addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
