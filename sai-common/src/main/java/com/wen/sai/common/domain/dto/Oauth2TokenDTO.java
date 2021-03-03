package com.wen.sai.common.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Oauth2 Token 数据传输类
 * </p>
 *
 * @author wenjun
 * @since 2021/2/23
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Oauth2TokenDTO", description = "Oauth2 Token 数据传输类")
public class Oauth2TokenDTO {

    @ApiModelProperty("访问令牌")
    private String token;

    @ApiModelProperty("刷新令牌")
    private String refreshToken;

    @ApiModelProperty("访问令牌头")
    private String tokenHead;

    @ApiModelProperty("过期时间（秒）")
    private Integer expire;
}
