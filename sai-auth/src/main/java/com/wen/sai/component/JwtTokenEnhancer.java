package com.wen.sai.component;

import com.wen.sai.common.domain.bo.AdminUserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * JWT 内容增强
 * </p>
 *
 * @author wenjun
 * @since 2021/2/21
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        AdminUserDetails adminUserDetails = (AdminUserDetails) oAuth2Authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>(16);
        info.put("id", adminUserDetails.getAdmin().getId());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
