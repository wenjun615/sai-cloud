package com.wen.sai.controller;

import com.wen.sai.common.api.CommonResult;
import com.wen.sai.common.api.ResultCodeImpl;
import com.wen.sai.common.domain.constant.AuthConstant;
import com.wen.sai.common.domain.dto.Oauth2TokenDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Map;

/**
 * <p>
 * 认证授权相关功能 Controller
 * </p>
 *
 * @author wenjun
 * @since 2021/2/23
 */
@RestController
@RequestMapping("/oauth")
@Api(tags = {"AuthController"})
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final TokenEndpoint tokenEndpoint;

    @ApiOperation("加载 Token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", value = "Oauth2 客户端 ID", required = true),
            @ApiImplicitParam(name = "client_secret", value = "Oauth2 客户端秘钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新 Token"),
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping("/token")
    public CommonResult<Oauth2TokenDTO> loadToken(
            @ApiIgnore Principal principal,
            @ApiIgnore @RequestParam Map<String, String> params) {
        OAuth2AccessToken oAuth2AccessToken = null;
        try {
            oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, params).getBody();
        } catch (HttpRequestMethodNotSupportedException e) {
            log.error(e.getMessage(), e);
        }
        if (oAuth2AccessToken == null) {
            return CommonResult.failed(ResultCodeImpl.FAILED, "登录失败！");
        }
        Oauth2TokenDTO oauth2TokenDTO = Oauth2TokenDTO.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX)
                .expire(oAuth2AccessToken.getExpiresIn())
                .build();
        return CommonResult.success(oauth2TokenDTO);
    }
}
