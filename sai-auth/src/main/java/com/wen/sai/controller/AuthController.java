package com.wen.sai.controller;

import com.wen.sai.common.api.CommonCode;
import com.wen.sai.common.api.CommonResult;
import com.wen.sai.common.entity.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;

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

    @ApiOperation("加载令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", value = "Oauth2 客户端 ID", required = true),
            @ApiImplicitParam(name = "client_secret", value = "Oauth2 客户端秘钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新令牌"),
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping("/token")
    public CommonResult<UserVO> loadToken(
            @ApiIgnore Principal principal,
            @ApiIgnore @RequestParam Map<String, String> paramMap) {
        OAuth2AccessToken oAuth2AccessToken;
        try {
            oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, paramMap).getBody();
        } catch (Exception e) {
            log.warn("登录异常：{}", e.getMessage(), e);
            oAuth2AccessToken = null;
        }
        if (Objects.isNull(oAuth2AccessToken)) {
            return CommonResult.failed(CommonCode.FAILURE, "登录失败");
        }
        UserVO userVO = UserVO.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .build();
        return CommonResult.successful(userVO, "登录成功");
    }
}
