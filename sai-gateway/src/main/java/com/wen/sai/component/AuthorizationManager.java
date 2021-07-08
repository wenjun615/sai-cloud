package com.wen.sai.component;

import cn.hutool.core.collection.CollUtil;
import com.google.common.base.Predicate;
import com.wen.sai.common.constant.AuthConstants;
import com.wen.sai.config.IgnoreProperties;
import com.wen.sai.service.AuthorityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 鉴权管理
 * </p>
 *
 * @author wenjun
 * @since 2021/2/22
 */
@Component
@AllArgsConstructor
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final IgnoreProperties ignoreProperties;

    private final AuthorityService authorityService;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // 跨域预检请求放行
        if (Objects.equals(request.getMethod(), HttpMethod.OPTIONS)) {
            return Mono.just(new AuthorizationDecision(true));
        }
        AntPathMatcher pathMatcher = new AntPathMatcher();
        URI uri = request.getURI();
        // 白名单放行
        for (String pattern : ignoreProperties.getUrls()) {
            if (pathMatcher.match(pattern, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        List<String> authorityList = new ArrayList<>();
        Map<Object, Object> authorityMap = authorityService.getAuthorityMap();
        for (Map.Entry<Object, Object> entry : authorityMap.entrySet()) {
            if (pathMatcher.match((String) entry.getKey(), uri.getPath())) {
                authorityList.add((String) entry.getValue());
            }
        }
        // 当前访问资源不需要权限，已认证即放行
        if (CollUtil.isEmpty(authorityList)) {
            return mono.filter(Authentication::isAuthenticated)
                    .flatMapIterable(Authentication::getAuthorities)
                    .map(GrantedAuthority::getAuthority)
                    .any((Predicate<String>) s -> true)
                    .map(AuthorizationDecision::new)
                    .defaultIfEmpty(new AuthorizationDecision(false));
        }
        authorityList = authorityList.stream()
                .map(authority -> AuthConstants.AUTHORITY_PREFIX + authority)
                .collect(Collectors.toList());
        // 已认证且角色权限足够放行
        return mono.filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorityList::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
