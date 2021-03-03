package com.wen.sai.component;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.wen.sai.common.domain.constant.AuthConstant;
import com.wen.sai.common.domain.constant.RedisKeyConstant;
import com.wen.sai.common.service.RedisService;
import com.wen.sai.config.IgnoreUrlsConfig;
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

    private final IgnoreUrlsConfig ignoreUrlsConfig;

    private final RedisService redisService;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // 跨域预检请求放行
        if (Objects.equals(request.getMethod(), HttpMethod.OPTIONS)) {
            return Mono.just(new AuthorizationDecision(true));
        }
        URI uri = request.getURI();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        // 白名单放行
        for (String url : ignoreUrlsConfig.getUrls()) {
            if (pathMatcher.match(url, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        // 当前访问的资源所需要的权限，用户拥有的权限满足其中任一即可
        List<String> authorities = new ArrayList<>();
        // 从缓存中获取 所有资源 与 对应角色 的 规则
        Map<Object, Object> resourceRolesMap = redisService.hGetAll(RedisKeyConstant.RESOURCE_ROLES_KEY);
        for (Map.Entry<Object, Object> entry : resourceRolesMap.entrySet()) {
            if (pathMatcher.match((String) entry.getKey(), uri.getPath())) {
                authorities.addAll(Convert.toList(String.class, entry.getValue()));
            }
        }
        // 当前访问资源不需要权限直接放行
        if (CollUtil.isEmpty(authorities)) {
            return Mono.just(new AuthorizationDecision(true));
        }
        authorities = authorities.stream()
                .map(authority -> AuthConstant.AUTHORITY_PREFIX + authority)
                .collect(Collectors.toList());
        // 已认证且角色权限足够放行
        return mono.filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
