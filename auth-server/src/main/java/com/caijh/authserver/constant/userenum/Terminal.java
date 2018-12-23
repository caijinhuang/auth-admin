/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-12-23 下午9:22
 *  @createTime: 2018-12-23 21:22:26
 *  @classPath: com.caijh.authserver.constant.userenum.Terminal
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.constant.userenum;

/**
 * @author cjh
 * 客户端枚举类
 */
public enum Terminal {
    /**
     * 客户端枚举
     */
    WEB("web"),
    微信("wechat"),
    安卓("android"),
    苹果("ios");

    private String name;

    Terminal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 是否合法的的终端请求
     * @param terminal
     * @return
     */
    public static boolean isLegal(String terminal) {
        for (Terminal t : Terminal.values()) {
            if (t.name.equals(terminal)) {
                return true;
            }
        }
        return false;
    }
}
