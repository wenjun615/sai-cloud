package com.wen.sai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.sai.model.RoleResourceRelation;

/**
 * <p>
 * 后台角色和资源关系表 服务类
 * </p>
 *
 * @author wenjun
 * @since 2021-02-20
 */
public interface RoleResourceRelationService extends IService<RoleResourceRelation> {

    /**
     * 初始化资源与角色关系
     *
     */
    void initResourceRoles();
}
