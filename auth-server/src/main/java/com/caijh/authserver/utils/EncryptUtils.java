/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-12-6 下午11:24
 *  @createTime: 2018-12-06 23:24:14
 *  @classPath: com.caijh.authserver.utils.EncryptUtils
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.utils;

import lombok.extern.log4j.Log4j2;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author cjh
 * 加密工具
 */
@Log4j2
public class EncryptUtils {

    /**
     * base64加密
     *
     * @param str 加密的字符串
     * @return encodeStr 加密后的字符串
     */
    public static String base64Encode(String str) {
        String encodeStr = null;
        try {
            encodeStr = Base64.getUrlEncoder().encodeToString(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("base64加密错误！", e);
        }
        return encodeStr;
    }

    /**
     * base64解密
     *
     * @param base64Str 加密的字符串
     * @return decodeStr 解密后的字符串
     */
    public static String base64Decode(String base64Str) {
        String decodeStr = null;
        try {
            decodeStr = new String(Base64.getUrlDecoder().decode(base64Str), "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("字符串编码失败", e);
        }
        return decodeStr;
    }


    /**
     * SHA256加密
     *
     * @return 加密后的字符串
     */
    public static String encode2SHA256(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes());
            return messageDigest.digest().toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("找不到对应的加密算法！", e);
        }
        return null;
    }

    /**
     * sha256_HMAC加密
     *
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */
    public static String sha256HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }

    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stamp;
        for (int n = 0; b != null && n < b.length; n++) {
            stamp = Integer.toHexString(b[n] & 0XFF);
            if (stamp.length() == 1) {
                hs.append('0');
            }
            hs.append(stamp);
        }
        return hs.toString().toLowerCase();
    }
}
