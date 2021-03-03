package com.wen.sai.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.sai.common.api.CommonResult;
import com.wen.sai.common.domain.bo.AdminUserDetails;
import com.wen.sai.common.domain.constant.AuthConstant;
import com.wen.sai.common.domain.dto.Oauth2TokenDTO;
import com.wen.sai.common.domain.query.AdminQuery;
import com.wen.sai.mapper.AdminMapper;
import com.wen.sai.model.Admin;
import com.wen.sai.model.Role;
import com.wen.sai.service.AdminCacheService;
import com.wen.sai.service.AdminRoleRelationService;
import com.wen.sai.service.AdminService;
import com.wen.sai.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2021-02-20
 */
@Service
@AllArgsConstructor
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    private final AdminCacheService adminCacheService;

    private final AdminRoleRelationService adminRoleRelationService;

    private final AuthService authService;

    @Override
    public AdminUserDetails loadUserByUsername(String username) {
        Admin admin = getByUsername(username);
        if (admin == null) {
            return null;
        }
        List<Role> roleList = adminRoleRelationService.listRolesByAdminId(admin.getId());
        return new AdminUserDetails(admin, roleList);
    }

    @Override
    public Admin getByUsername(String username) {
        Admin admin = adminCacheService.get(username);
        if (admin != null) {
            return admin;
        }
        List<Admin> adminList = lambdaQuery()
                .eq(Admin::getUsername, username)
                .list();
        if (CollUtil.isEmpty(adminList)) {
            return null;
        }
        adminCacheService.set(adminList.get(0));
        return adminList.get(0);
    }

    @Override
    public CommonResult<Oauth2TokenDTO> login(AdminQuery adminQuery) {
        Map<String, String> params = new HashMap<>(16);
        params.put("client_id", AuthConstant.ADMIN_CLIENT_ID);
        params.put("client_secret", "123456");
        params.put("grant_type", "password");
        params.put("username", adminQuery.getUsername());
        params.put("password", adminQuery.getPassword());
        return authService.loadToken(params);
    }
}
