/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-7 上午12:29
 *  @createTime: 2018-11-07 00:30:11
 *  @classPath: com.caijh.authserver.constant.userenum.AccountType
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.constant.userenum;

import lombok.Data;

/**
 * @author cjh
 * 用户账户
 */
public enum AccountType {
    /**
     * 邮箱
     */
    Email("邮箱", "email"),
    /**
     * 电话号码
     */
    Phone("电话号码", "phone");

    AccountType(String label,String key) {
        this.label = label;
        this.key = key;
    }

    /**
     * 获取账户类型的美剧对象
     * @param typeKey 账户类型关键词
     * @return 对应的枚举对象
     */
    public static AccountType getType(String typeKey){
        for (AccountType item : AccountType.values()) {
            if(item.key.equals(typeKey)){
                return item;
            }
        }
        return null;
    }

    private String label;
    private String key;
}
