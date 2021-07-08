package com.wen.sai.service;

import cn.hutool.core.collection.CollUtil;
import com.wen.sai.common.service.RedisService;
import com.wen.sai.config.GatewayRedisProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 权限 Service
 * </p>
 *
 * @author wenjun
 * @since 2021-07-06
 */
@Service
@AllArgsConstructor
public class AuthorityService {

    private final RedisService redisService;

    private final ResourceService resourceService;

    private final GatewayRedisProperties gatewayRedisProperties;

    /**
     * 获取资源与权限对应关系
     *
     * @return 资源与权限对应关系
     */
    public Map<Object, Object> getAuthorityMap() {
        Map<Object, Object> authorityMap = redisService.hGetAll(gatewayRedisProperties.getKey().getAuthorities());
        if (CollUtil.isEmpty(authorityMap)) {
            authorityMap = resourceService.loadAuthorityMap().getData();
        }
        return authorityMap;
    }
}
