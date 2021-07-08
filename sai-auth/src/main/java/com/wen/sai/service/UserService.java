package com.wen.sai.service;

import com.wen.sai.common.entity.bo.UserDetailsBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 用户相关功能远程调用
 * </p>
 *
 * @author wenjun
 * @since 2021-07-04
 */
@FeignClient("sai-admin")
public interface UserService {

    /**
     * 根据用户名加载用户
     *
     * @param username 用户名
     * @return 用户
     */
    @GetMapping("/user/loadUserByUsername")
    UserDetailsBO loadUserByUsername(@RequestParam String username);
}
