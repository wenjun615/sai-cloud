package com.wen.sai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.sai.common.domain.constant.RedisKeyConstant;
import com.wen.sai.common.service.RedisService;
import com.wen.sai.mapper.ResourceMapper;
import com.wen.sai.mapper.RoleMapper;
import com.wen.sai.mapper.RoleResourceRelationMapper;
import com.wen.sai.model.Resource;
import com.wen.sai.model.Role;
import com.wen.sai.model.RoleResourceRelation;
import com.wen.sai.service.RoleResourceRelationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台角色和资源关系表 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2021-02-20
 */
@Service
public class RoleResourceRelationServiceImpl extends ServiceImpl<RoleResourceRelationMapper, RoleResourceRelation> implements RoleResourceRelationService {

    private final ResourceMapper resourceMapper;

    private final RoleMapper roleMapper;

    private final RedisService redisService;

    public RoleResourceRelationServiceImpl(ResourceMapper resourceMapper, RoleMapper roleMapper,
                                           RedisService redisService) {
        this.resourceMapper = resourceMapper;
        this.roleMapper = roleMapper;
        this.redisService = redisService;
    }

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void initResourceRoles() {
        // TreeMap 默认升序
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();
        List<Resource> resourceList = resourceMapper.selectList(null);
        List<Role> roleList = roleMapper.selectList(null);
        List<RoleResourceRelation> roleResourceRelationList = baseMapper.selectList(null);
        for (Resource resource : resourceList) {
            Set<String> roleIdSet = roleResourceRelationList.stream()
                    .filter(roleResourceRelation -> roleResourceRelation.getResourceId().equals(resource.getId()))
                    .map(RoleResourceRelation::getRoleId)
                    .collect(Collectors.toSet());
            List<String> authorities = roleList.stream()
                    .filter(role -> roleIdSet.contains(role.getId()))
                    .map(role -> role.getId() + "_" + role.getName())
                    .collect(Collectors.toList());
            resourceRolesMap.put("/" + applicationName + resource.getUrl(), authorities);
        }
        redisService.hSetAll(RedisKeyConstant.RESOURCE_ROLES_KEY, resourceRolesMap);
    }
}
