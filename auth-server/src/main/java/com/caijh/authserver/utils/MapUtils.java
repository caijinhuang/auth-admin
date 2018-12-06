/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-13 下午11:43
 *  @createTime: 2018-11-13 23:43:52
 *  @classPath: com.caijh.authserver.utils.MapUtils
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author cjh
 * Map操作工具类
 */
@Log4j2
public class MapUtils {

    /**
     * 对象转成Map
     *
     * @param obj 待转对象
     * @return 转换后的Map对象
     */
    public static Map objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map beanMap = new BeanMap(obj);
        return beanMap;
    }

    /**
     * map转换成指定对象
     *
     * @param map   待转Map对象
     * @param clazz 目标对象类名
     * @param <T>   泛型
     * @return 转换后的对象
     */
    public static <T> T mapToObject(Map map, Class<T> clazz) {
        Object obj = new Object();
        try {
            obj = clazz.newInstance();
            BeanUtils.populate(obj, map);
        } catch (IllegalAccessException e) {
            log.error("map转成对象错误,非法访问异常！", e);
        } catch (InvocationTargetException e) {
            log.error("map转成对象错误,目标对象执行异常！", e);
        } catch (InstantiationException e) {
            log.error("map转成对象错误,对象反射异常！", e);
        }
        return (T) obj;
    }

    /**
     * 直接将对象转换成指定的对象
     *
     * @param obj   原始对象
     * @param clazz 目标对象的类
     * @param <T>   泛型
     * @return 对象转成对象
     */
    public static <T> T objectToObject(Object obj, Class<T> clazz) {
        Map map = objectToMap(obj);
        Object newObj = mapToObject(map, clazz);
        return (T) newObj;
    }
}
