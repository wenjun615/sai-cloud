package com.wen.sai.common.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户显示层对象
 * </p>
 *
 * @author wenjun
 * @since 2021-06-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {

    /**
     * 访问令牌
     */
    private String token;

    /**
     * 刷新令牌
     */
    private String refreshToken;
}
