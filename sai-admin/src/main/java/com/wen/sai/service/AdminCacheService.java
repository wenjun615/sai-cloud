package com.wen.sai.service;

import com.wen.sai.model.Admin;

/**
 * <p>
 * 后台用户缓存管理 Service
 * </p>
 *
 * @author wenjun
 * @since 2021/2/21
 */
public interface AdminCacheService {

    /**
     * 根据用户名获取单个后台用户信息
     *
     * @param username 用户名
     * @return 后台用户信息
     */
    Admin get(String username);

    /**
     * 设置后台用户信息
     *
     * @param admin 后台用户信息
     */
    void set(Admin admin);
}
