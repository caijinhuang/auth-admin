/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-4 下午9:15
 *  @createTime: 2018-11-04 21:21:37
 *  @classPath: com.caijh.authserver.dao.jpa.UserDao
 **************************************************************************************************/

package com.caijh.authserver.dao.jpa;

import com.caijh.authserver.entity.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cjh
 * 用户信息持久化操作
 */
@Repository
public interface UserDao extends JpaRepository<User, String> {
    /**
     * 查询用户账户类型和手机号码是否存在
     * @param accountType 用户账户类型
     * @param phone 手机号码
     * @return 用户对象
     */
    User findUserByAccountTypeAndPhone(String accountType,String phone);

    /**
     * 查询用和账户类型和邮件是否存在
     * @param accountType 用户账户类型
     * @param email 邮箱
     * @return 用户对象
     */
    User findUserByAccountTypeAndEmail(String accountType,String email);
}
