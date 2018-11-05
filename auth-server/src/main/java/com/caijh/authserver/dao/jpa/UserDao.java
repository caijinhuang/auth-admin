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
}
