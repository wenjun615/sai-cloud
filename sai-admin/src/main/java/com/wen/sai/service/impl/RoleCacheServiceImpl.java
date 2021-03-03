package com.wen.sai.service.impl;

import com.wen.sai.common.service.RedisService;
import com.wen.sai.model.Role;
import com.wen.sai.service.RoleCacheService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台角色缓存管理 ServiceImpl
 * </p>
 *
 * @author wenjun
 * @since 2021/2/21
 */
@Service
public class RoleCacheServiceImpl implements RoleCacheService {

    private final RedisService redisService;

    public RoleCacheServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Value("${redis.database}")
    private String database;

    @Value("${redis.key.roleList}")
    private String roleListKey;

    @Value("${redis.expire}")
    private Long expire;

    @Override
    public List<Role> listByAdminId(String adminId) {
        String key = database + ":" + roleListKey + ":" + adminId;
        return (List<Role>) redisService.get(key);
    }

    @Override
    public void setsByAdminId(String adminId, List<Role> roleList) {
        String key = database + ":" + roleListKey + ":" + adminId;
        redisService.set(key, roleList, expire);
    }
}
