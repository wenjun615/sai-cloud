package com.wen.sai.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 登录用户信息
 * </p>
 *
 * @author wenjun
 * @since 2021/2/22
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    /**
     * ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 权限
     */
    private List<String> authorities;
}
