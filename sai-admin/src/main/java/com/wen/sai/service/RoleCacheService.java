package com.wen.sai.service;

import com.wen.sai.model.Role;

import java.util.List;

/**
 * <p>
 * 后台角色缓存管理 Service
 * </p>
 *
 * @author wenjun
 * @since 2021/2/21
 */
public interface RoleCacheService {

    /**
     * 根据后台用户 ID 获取后台角色缓存列表
     *
     * @param adminId 后台用户 ID
     * @return 后台角色缓存列表
     */
    List<Role> listByAdminId(String adminId);

    /**
     * 根据后台用户 ID 设置后台角色缓存列表
     *
     * @param adminId  后台用户 ID
     * @param roleList 后台角色缓存列表
     */
    void setsByAdminId(String adminId, List<Role> roleList);
}
