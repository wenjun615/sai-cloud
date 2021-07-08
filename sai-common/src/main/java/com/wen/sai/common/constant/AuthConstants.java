package com.wen.sai.common.constant;

/**
 * <p>
 * 认证授权相关功能常量
 * </p>
 *
 * @author wenjun
 * @since 2021/2/22
 */
public class AuthConstants {

    /**
     * 认证信息 Http 请求头
     */
    public static final String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT Token 前缀
     */
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * JWT 存储的用户权限的属性名
     */
    public static final String CLAIM_AUTHORITY_NAME = "authorities";

    /**
     * JWT 存储的用户权限的前缀
     */
    public static final String AUTHORITY_PREFIX = "ROLE_";

    /**
     * 用户信息 Http 请求头
     */
    public static final String USER_HEADER = "user";
}
