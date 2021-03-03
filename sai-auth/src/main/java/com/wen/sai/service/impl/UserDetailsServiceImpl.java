package com.wen.sai.service.impl;

import com.wen.sai.common.domain.bo.AdminUserDetails;
import com.wen.sai.common.domain.constant.AuthMessageConstant;
import com.wen.sai.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Spring Security 用户业务功能实现
 * </p>
 *
 * @author wenjun
 * @since 2021/2/21
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AdminUserDetails adminUserDetails = adminService.loadUserByUsername(s);
        if (adminUserDetails == null) {
            throw new UsernameNotFoundException(AuthMessageConstant.USERNAME_PASSWORD_ERROR);
        }
        return adminUserDetails;
    }
}
