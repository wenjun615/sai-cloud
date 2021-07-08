package com.wen.sai.component;

import com.google.common.collect.Maps;
import com.wen.sai.common.entity.bo.UserDetailsBO;
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
        UserDetailsBO userDetailsBO = (UserDetailsBO) oAuth2Authentication.getPrincipal();
        Map<String, Object> userInfoMap = Maps.newHashMap();
        userInfoMap.put("id", userDetailsBO.getUser().getId());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(userInfoMap);
        return oAuth2AccessToken;
    }
}
