package com.wen.sai.service;

import com.wen.sai.common.domain.bo.AdminUserDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 后台用户相关功能远程调用
 * </p>
 *
 * @author wenjun
 * @since 2021/2/20
 */
@FeignClient("sai-admin")
public interface AdminService {

    /**
     * 根据用户名加载登录用户信息
     *
     * @param username 用户名
     * @return 登录用户信息
     */
    @GetMapping("/admin/loadUserByUsername")
    AdminUserDetails loadUserByUsername(@RequestParam String username);
}
