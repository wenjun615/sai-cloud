package com.wen.sai.service;

import com.wen.sai.model.AdminRoleRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.sai.model.Role;

import java.util.List;

/**
 * <p>
 * 后台用户和角色关系表 服务类
 * </p>
 *
 * @author wenjun
 * @since 2021-02-20
 */
public interface AdminRoleRelationService extends IService<AdminRoleRelation> {

    /**
     * 根据后台用户 ID 获取后台角色列表
     *
     * @param adminId 后台用户 ID
     * @return 后台角色列表
     */
    List<Role> listRolesByAdminId(String adminId);
}
