package com.wen.sai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wen.sai.model.AdminRoleRelation;
import com.wen.sai.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户和角色关系表 Mapper 接口
 * </p>
 *
 * @author wenjun
 * @since 2021-02-20
 */
public interface AdminRoleRelationMapper extends BaseMapper<AdminRoleRelation> {

    /**
     * 根据后台用户 ID 获取后台角色列表
     *
     * @param adminId 后台用户 ID
     * @return 后台角色列表
     */
    List<Role> listByAdminId(@Param("adminId") String adminId);
}
