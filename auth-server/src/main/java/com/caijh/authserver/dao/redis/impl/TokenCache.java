/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-12-22 下午10:05
 *  @createTime: 2018-12-22 22:05:44
 *  @classPath: com.caijh.authserver.dao.redis.impl.TokenDao
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.dao.redis.impl;

import com.caijh.authserver.entity.db.User;
import com.caijh.authserver.entity.query.LoginUser;
import com.caijh.authserver.utils.StringUtils;
import io.netty.util.internal.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author cjh
 * token缓存对象
 * token的key存储规则为 模块名:客户端:账户信息:token
 * 例如：auth:pc:phone158808****41:32位token
 */
@Repository
@Log4j2
public class TokenCache extends RedisBaseOptionImpl {

    /**
     * 设置token
     *
     * @param token      token
     * @param user       登陆用户的信息
     * @param expireTime 过期时间
     * @return redis主键
     */
    public String setToken(String token, User user, String terminal, long expireTime) {
        String redisKey = getTokenKey(token, user, terminal);
        String realKey = getTokenKeyFromRedis(getTokenKeyPrefix(user, terminal));
        if (!StringUtil.isNullOrEmpty(realKey)) {
            remove(realKey);
        }
        set(redisKey, user, expireTime);
        return redisKey;
    }

    /**
     * 获取请求对象token信息
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request){
        String token = request.getHeader("ACCESS-TOKEN");
        Cookie[] cookies = request.getCookies();
        if (token == null && cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }


    /**
     * 根据token获取用户信息
     *
     * @param token token
     * @return 用户信息
     */
    public User getUserInfoByToken(String token, String terminal) {
        String key = getTokenKeyFromRedis(token);
        if (StringUtils.isEmpty(key) || !key.contains(":" + terminal + ":")) {
            return null;
        }

        User user;
        try {
            user = (User) get(key);
        } catch (Exception e) {
            log.error("redis获取用户信息失败：{}", e);
            return null;
        }
        return user;
    }

    /**
     * 根据关键字获取redis上的真实的主键
     *
     * @param keyString 关键字
     * @return 真实主键
     */
    public String getTokenKeyFromRedis(String keyString) {
        Set keys = keys(keyString, "*", "*");
        if (keys.isEmpty()) {
            return null;
        }
        String key = keys.toArray()[0].toString();
        return key;
    }

    /**
     * 生成redis主键
     *
     * @param key
     * @param user
     * @return
     */
    private String getTokenKey(String key, User user, String terminal) {
        String token = StringUtil.isNullOrEmpty(key) ? StringUtils.getUUID() : key;
        String prefix = getTokenKeyPrefix(user, terminal);
        String userKey = prefix + ":" + token;
        return userKey;
    }

    /**
     * 获取token关键字前缀
     *
     * @param user 用户信息
     * @return key前缀
     */
    private String getTokenKeyPrefix(User user, String terminal) {
        String accountType = user.getAccountType();
        String account = user.getAccountByType(accountType);
        String[] prefixArray = {KEY_PRIFIX, terminal, accountType, account};
        return StringUtils.join(prefixArray, ":");
    }

}
