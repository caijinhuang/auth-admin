/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-12-22 上午10:36
 *  @createTime: 2018-12-22 21:34:05
 *  @classPath: com.caijh.authserver.dao.redis.impl.RedisServiceImpl
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.dao.redis.impl;

import com.caijh.authserver.dao.redis.api.RedisBaseOption;
import com.caijh.authserver.utils.MapUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author cjh
 * redis服务接口实现类
 */
@Component
@Log4j2
public class RedisBaseOptionImpl implements RedisBaseOption {

    /**
     * 前缀
     */
    public static final String KEY_PRIFIX = "auth";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean set(String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            log.error("redis插入数据失败", e);
            throw new RuntimeException("redis数据保存异常！");
        }
        return result;
    }

    @Override
    public boolean set(String key, Object value, long expireTime) {
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set(key, value, expireTime, TimeUnit.MINUTES);
        return false;
    }

    @Override
    public void remove(String... keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public void removePattern(String pattern) {
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean exists(String key) {
        return false;
    }

    @Override
    public Object get(String key) {
        ValueOperations options = redisTemplate.opsForValue();
        return options.get(key);
    }

    @Override
    public Set keys(String key, String prefix, String suffix) {
        return redisTemplate.keys(prefix + key + suffix);
    }

    @Override
    public void hashSet(String key, Object hashKey, Object value) {
        HashOperations operations = redisTemplate.opsForHash();
        operations.putAll(key, MapUtils.objectToMap(value));
    }

    @Override
    public Object hmGet(String key, Object hashKey) {
        return null;
    }

    @Override
    public void listPush(String key, Object value) {

    }

    @Override
    public List<Object> listRange(String key, long start, long end) {
        return null;
    }

    @Override
    public void add(String key, Object value) {

    }

    @Override
    public Set<Object> setMembers(String key) {
        return null;
    }

    @Override
    public void zAdd(String key, Object value, double scoure) {

    }

    @Override
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        return null;
    }
}
