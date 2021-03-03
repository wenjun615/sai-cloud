package com.wen.sai.common.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 后台用户相关功能请求参数
 * </p>
 *
 * @author wenjun
 * @since 2021/2/23
 */
@Data
@ApiModel(value = "Admin参数", description = "后台用户相关功能请求参数")
public class AdminQuery {

    @NotBlank
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
