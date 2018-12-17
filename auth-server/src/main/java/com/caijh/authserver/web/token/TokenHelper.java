/***************************************************************************************************
 *  Copyright (c) 2018  the original author or authors
 *  @author: 小Cai先森
 *  @lastModified: 18-12-6 下午10:23
 *  @createTime: 2018-12-06 22:23:05
 *  @classPath: com.caijh.authserver.web.token.CreateToken
 *  @blog: http://www.cnblogs.com/caijh/default.html?page=1
 **************************************************************************************************/

package com.caijh.authserver.web.token;

import com.alibaba.fastjson.JSON;
import com.caijh.authserver.constant.message.AuthHint;
import com.caijh.authserver.entity.token.JwtBody;
import com.caijh.authserver.entity.token.JwtHead;
import com.caijh.authserver.entity.token.Token;
import com.caijh.authserver.entity.view.LoginUser;
import com.caijh.authserver.utils.EncryptUtils;
import com.caijh.authserver.utils.StringUtils;
import lombok.extern.log4j.Log4j2;

import java.util.Calendar;

/**
 * @author cjh
 * 创建token
 */
@Log4j2
public class TokenHelper {
    /**
     * token签名密钥
     */
    private static final String SECRET = "codeCjhAdmin2018";
    /**
     * token签发人
     */
    private static final String ISSUER = "codecjh@163.com";
    /**
     * 有效期【单位：分钟】
     */
    private static final int EXPIRY_DATE = 30;
    /**
     * token超期保留时间
     */
    private static final int REFRESH_RANGE = 15;
    /**
     * 签名加密类型
     */
    private static final String ENCRYPT_TYPE = "SHA-256";

    /**
     * 创建token
     *
     * @param userInfo 用户信息
     * @return token字符串
     */
    public static String create(LoginUser userInfo) {
        /**
         * 时间戳
         */
        long timeStamp = Calendar.getInstance().getTimeInMillis();
        /**
         * 有效期
         */
        long expiryDate = timeStamp + (EXPIRY_DATE * 60 * 1000);
        JwtHead head = JwtHead.builder().alg(ENCRYPT_TYPE).type("JWT").build();
        JwtBody body = JwtBody.builder()
                .issuer(ISSUER)
                .issueDate(String.valueOf(timeStamp))
                .expiryDate(String.valueOf(expiryDate))
                .tokenId(StringUtils.getUUID())
                .userInfo(userInfo)
                .build();
        String headJson = JSON.toJSONString(head);
        String bodyJson = JSON.toJSONString(body);
        String headBase64 = EncryptUtils.base64Encode(headJson);
        String bodyBase64 = EncryptUtils.base64Encode(bodyJson);
        String signature = EncryptUtils.sha256HMAC(headBase64 + "." + bodyBase64, SECRET);
        return headBase64 + "." + bodyBase64 + "." + signature;
    }


    /**
     * 解析token
     *
     * @param tokenStr  token字符串
     * @return token校验结果对象
     */
    public static Token parseToken(String tokenStr) {

        /**
         * token有效性验证
         */
        String[] jwt = tokenStr.split("\\.");
        if (jwt.length != 3) {
            log.warn(AuthHint.TOKEN_INVALID);
            return Token.builder().msg(AuthHint.TOKEN_INVALID).build();
        }

        /**
         * 签名有效性验证
         */
        boolean signatureIsCorrect = jwt[2].equals(EncryptUtils.sha256HMAC(jwt[0] + "." + jwt[1], SECRET));
        if (!signatureIsCorrect) {
            log.warn(AuthHint.SIGNATURE_ERROR);
            return Token.builder().msg(AuthHint.SIGNATURE_ERROR).build();
        }

        /**
         * token超期验证
         * 在超期15分钟范围内允许重新获取token
         */
        String headJson = EncryptUtils.base64Decode(jwt[0]);
        String bodyJson = EncryptUtils.base64Decode(jwt[1]);
        JwtBody body = JSON.parseObject(bodyJson, JwtBody.class);
        JwtHead head = JSON.parseObject(headJson, JwtHead.class);
        long timeStamp = Calendar.getInstance().getTimeInMillis();
        long diffTime = timeStamp - Long.valueOf(body.getExpiryDate());
        boolean isExpired = diffTime > 0;
        if (isExpired) {
            boolean canRefresh = diffTime < REFRESH_RANGE * 60 * 1000;
            log.warn(AuthHint.TOKEN_EXPIRED);
            return Token.builder().canRefresh(canRefresh).msg(AuthHint.TOKEN_EXPIRED).build();
        }

        return Token.builder()
                .isValid(true)
                .body(body)
                .head(head)
                .signature(jwt[2])
                .build();
    }
}