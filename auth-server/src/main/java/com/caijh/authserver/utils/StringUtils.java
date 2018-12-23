/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-11-11 下午3:02
 *  @createTime: 2018-11-11 15:02:10
 *  @classPath: com.caijh.authserver.utils.StringUtils
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.utils;

import java.util.UUID;

/**
 * @author cjh
 * 字符串处理工具
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    /**
     * 首字母转成大写
     *
     * @param str 要转换的字符串
     * @return 转换后的字符串
     */
    public static String toUpperCase(String str) {
        char[] ch = str.toCharArray();
        ch[0] -= 32;
        return String.valueOf(ch);
    }

    /**
     * 获取UUID
     * @return uuid
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid;
    }
}
