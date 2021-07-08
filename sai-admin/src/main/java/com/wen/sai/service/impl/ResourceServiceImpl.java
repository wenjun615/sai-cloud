package com.wen.sai.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.sai.common.service.RedisService;
import com.wen.sai.config.AdminRedisProperties;
import com.wen.sai.mapper.ResourceMapper;
import com.wen.sai.model.Resource;
import com.wen.sai.service.ResourceCacheService;
import com.wen.sai.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2021-03-21
 */
@Service
@AllArgsConstructor
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private final ResourceCacheService resourceCacheService;

    private final ResourceMapper resourceMapper;

    private final AdminRedisProperties adminRedisProperties;

    private final RedisService redisService;

    @Override
    public List<Resource> listByUserId(String userId) {
        List<Resource> resourceList = resourceCacheService.listByUserId(userId);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        resourceList = resourceMapper.listByUserId(userId);
        if (CollUtil.isEmpty(resourceList)) {
            return null;
        }
        resourceCacheService.setResources(userId, resourceList);
        return resourceList;
    }

    @Override
    public Map<Object, Object> getAuthorityMap() {
        String authorities = adminRedisProperties.getKey().getAuthorities();
        Map<Object, Object> authorityMap = redisService.hGetAll(authorities);
        if (CollUtil.isEmpty(authorityMap)) {
            List<Resource> resourceList = list();
            authorityMap = new HashMap<>(resourceList.size());
            for (Resource resource : resourceList) {
                authorityMap.put(resource.getPath(), resource.getId());
            }
        }
        redisService.hSetAll(authorities, authorityMap);
        return authorityMap;
    }
}
