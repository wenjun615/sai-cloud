package com.wen.sai.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wen.sai.model.AdminRoleRelation;
import com.wen.sai.mapper.AdminRoleRelationMapper;
import com.wen.sai.model.Role;
import com.wen.sai.service.AdminRoleRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.sai.service.RoleCacheService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台用户和角色关系表 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2021-02-20
 */
@Service
@AllArgsConstructor
public class AdminRoleRelationServiceImpl extends ServiceImpl<AdminRoleRelationMapper, AdminRoleRelation> implements AdminRoleRelationService {

    private final RoleCacheService roleCacheService;

    @Override
    public List<Role> listRolesByAdminId(String adminId) {
        List<Role> roleList = roleCacheService.listByAdminId(adminId);
        if (CollUtil.isNotEmpty(roleList)) {
            return roleList;
        }
        roleList = baseMapper.listByAdminId(adminId);
        if (CollUtil.isEmpty(roleList)) {
            return null;
        }
        roleCacheService.setsByAdminId(adminId, roleList);
        return roleList;
    }
}
