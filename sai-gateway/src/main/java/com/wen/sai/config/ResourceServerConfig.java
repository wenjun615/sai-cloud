package com.wen.sai.config;

import cn.hutool.core.util.ArrayUtil;
import com.wen.sai.common.domain.constant.AuthConstant;
import com.wen.sai.component.IgnoreUrlsRemoveJwtFilter;
import com.wen.sai.component.AuthorizationManager;
import com.wen.sai.component.RestfulAccessDeniedHandler;
import com.wen.sai.component.RestfulAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * <p>
 * 资源服务器配置
 * </p>
 *
 * @author wenjun
 * @since 2021/2/22
 */
@Configuration
@AllArgsConstructor
@EnableWebFluxSecurity
public class ResourceServerConfig {

    private final IgnoreUrlsConfig ignoreUrlsConfig;

    private final AuthorizationManager authorizationManager;

    private final RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;

    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    private final IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        /*serverHttpSecurity.oauth2ResourceServer()
                .authenticationEntryPoint(restfulAuthenticationEntryPoint)
                .accessDeniedHandler(restfulAccessDeniedHandler);*/
        serverHttpSecurity.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        serverHttpSecurity.authorizeExchange()
                // 白名单路径允许访问，ArrayUtil.toArray() 集合转数组方法
                .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(), String.class))
                .permitAll()
                .anyExchange()
                .access(authorizationManager)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restfulAuthenticationEntryPoint)
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .and()
                .csrf()
                .disable();
        return serverHttpSecurity.build();
    }

    /**
     * 将 JWT 转化成 Authentication
     */
    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // 给用户角色权限加上 ROLE_ 前缀，因为匹配权限的时候，访问资源所需的权限会加上 ROLE_
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstant.AUTHORITY_PREFIX);
        // 使用 authorities 表示角色权限，默认使用 scope
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstant.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
