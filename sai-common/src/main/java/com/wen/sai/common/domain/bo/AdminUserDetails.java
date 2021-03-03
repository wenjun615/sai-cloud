package com.wen.sai.common.domain.bo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wen.sai.common.domain.AuthorityDeserializer;
import com.wen.sai.model.Admin;
import com.wen.sai.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Spring Security 用户详情业务对象
 * </p>
 *
 * @author wenjun
 * @since 2021/2/21
 */
@Data
@AllArgsConstructor
public class AdminUserDetails implements UserDetails {

    private static final long serialVersionUID = 8049052807559420117L;

    /**
     * 后台用户
     */
    private Admin admin;

    /**
     * 后台角色列表
     */
    private List<Role> roleList;

    @Override
    @JsonDeserialize(using = AuthorityDeserializer.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getId() + "_" + role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return admin.getStatus().equals(1);
    }
}
