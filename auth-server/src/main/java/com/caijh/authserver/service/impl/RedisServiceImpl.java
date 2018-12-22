/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-12-18 下午11:19
 *  @createTime: 2018-12-18 23:19:42
 *  @classPath: com.caijh.authserver.service.impl.RedisServiceImpl
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.service.impl;

import com.caijh.authserver.service.api.RedisService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author cjh
 * redis服务接口实现类
 */
@Service
@Log4j2
public class RedisServiceImpl implements RedisService {

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
        }
        return result;
    }

    @Override
    public boolean set(String key, Object value, Long expireTime) {
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set(key, value, expireTime, TimeUnit.MINUTES);
        return false;
    }

    @Override
    public void remove(String... keys) {

    }

    @Override
    public void removePattern(String pattern) {

    }

    @Override
    public void remove(String key) {

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
    public void hashSet(String key, Object hashKey, Object value) {

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
