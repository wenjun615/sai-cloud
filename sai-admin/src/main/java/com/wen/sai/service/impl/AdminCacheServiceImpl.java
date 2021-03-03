package com.wen.sai.service.impl;

import com.wen.sai.common.service.RedisService;
import com.wen.sai.model.Admin;
import com.wen.sai.service.AdminCacheService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户缓存管理 ServiceImpl
 * </p>
 *
 * @author wenjun
 * @since 2021/2/21
 */
@Service
public class AdminCacheServiceImpl implements AdminCacheService {

    private final RedisService redisService;

    public AdminCacheServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Value("${redis.database}")
    private String database;

    @Value("${redis.key.admin}")
    private String adminKey;

    @Value("${redis.expire}")
    private Long expire;

    @Override
    public Admin get(String username) {
        String key = database + ":" + adminKey + ":" + username;
        return (Admin) redisService.get(key);
    }

    @Override
    public void set(Admin admin) {
        String key = database + ":" + this.adminKey + ":" + admin.getUsername();
        redisService.set(key, admin, expire);
    }
}
