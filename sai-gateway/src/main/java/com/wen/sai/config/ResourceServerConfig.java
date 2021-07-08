package com.wen.sai.config;

import cn.hutool.core.util.ArrayUtil;
import com.wen.sai.common.constant.AuthConstants;
import com.wen.sai.component.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
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
@AllArgsConstructor
@EnableWebFluxSecurity
public class ResourceServerConfig {

    private final IgnoreProperties ignoreProperties;

    private final AuthorizationManager authorizationManager;

    private final RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;

    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    private final IgnoreFilter ignoreFilter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.csrf()
                .disable()
                .addFilterBefore(ignoreFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange()
                // ArrayUtil.toArray() 集合转数组方法
                .pathMatchers(ArrayUtil.toArray(ignoreProperties.getUrls(), String.class))
                .permitAll()
                .anyExchange()
                .access(authorizationManager)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restfulAuthenticationEntryPoint)
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        return serverHttpSecurity.build();
    }

    /**
     * JWT Authentication 转换器
     */
    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // 给用户角色权限加上 ROLE_ 前缀，鉴权时，请求资源所需的权限会加上 ROLE_
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstants.AUTHORITY_PREFIX);
        // 使用 authorities 表示角色权限，默认使用 scope
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstants.CLAIM_AUTHORITY_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
