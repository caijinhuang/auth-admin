/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-12-22 下午10:05
 *  @createTime: 2018-12-22 22:05:44
 *  @classPath: com.caijh.authserver.dao.redis.impl.TokenDao
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.dao.redis.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

/**
 * @author cjh
 * token缓存对象
 */
@Repository
@Log4j2
public class TokenCache extends RedisBaseOptionImpl {

    public static final String KEY_TOKEN = KEY_PRIFIX_VALUE + "token:";

    public boolean setToken(String key, Object value) {
        return set(KEY_TOKEN + key, value);
    }

    public boolean setToken(String key, Object value, long expireTime) {
        return set(KEY_TOKEN + key, value, expireTime);
    }

    public Object getToken(String key) {
        return get(KEY_TOKEN + key);
    }

}
