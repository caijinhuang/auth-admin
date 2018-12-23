/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-12-22 上午10:34
 *  @createTime: 2018-12-22 21:33:55
 *  @classPath: com.caijh.authserver.dao.redis.api.RedisService
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.dao.redis.api;

import java.util.List;
import java.util.Set;

/**
 * @author cjh
 * redis服务
 */
public interface RedisBaseOption {


    /**
     * 写入缓存
     *
     * @param key   主键
     * @param value 内容
     * @return 是否写入成功
     */
    public  boolean set(final String key, Object value);

    /**
     * 写入缓存时设置时效
     *
     * @param key        主键
     * @param value      内容
     * @param expireTime 有效期【单位分钟】
     * @return 是否写入成功
     */
    public boolean set(final String key, Object value, long expireTime);

    /**
     * 批量删除对应的value
     *
     * @param keys 主键数组
     */
    public void remove(final String... keys);

    /**
     * 批量删除对应的value
     *
     * @param pattern 键值模式
     */
    public void removePattern(final String pattern);

    /**
     * 删除对应的value
     *
     * @param key 主键
     */
    public void remove(final String key);

    /**
     * 判断是否有对应的value
     *
     * @param key 主键
     * @return 是否存在
     */
    public boolean exists(final String key);

    /**
     * 读取缓存
     *
     * @param key 主键
     * @return 缓存内容
     */
    public Object get(final String key);

    /**
     * 模糊查询关键字
     * @param key
     * @return
     */
    public Set keys(String key, String prefix, String suffix);

    /**
     * 哈希添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hashSet(String key, Object hashKey, Object value);

    /**
     * 哈希获取
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey);

    /**
     * 列表添加
     *
     * @param key
     * @param value
     */
    public void listPush(String key, Object value);

    /**
     * 列表获取
     *
     * @param key
     * @param start 起始位置
     * @param end   结束位置
     * @return
     */
    public List<Object> listRange(String key, long start, long end);

    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value);

    /**
     * 结合获取
     *
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key);

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure);

    /**
     * 有序集合获取
     *
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1);
}
