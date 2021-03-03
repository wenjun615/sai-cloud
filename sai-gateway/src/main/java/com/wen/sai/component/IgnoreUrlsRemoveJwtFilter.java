package com.wen.sai.component;

import com.wen.sai.common.domain.constant.AuthConstant;
import com.wen.sai.config.IgnoreUrlsConfig;
import lombok.AllArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * <p>
 * 白名单路径请求移除 JWT 请求头
 * </p>
 *
 * @author wenjun
 * @since 2021/2/22
 */
@Component
@AllArgsConstructor
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    private final IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        URI uri = request.getURI();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String url : ignoreUrlsConfig.getUrls()) {
            if (pathMatcher.match(url, uri.getPath())) {
                request = request.mutate().header(AuthConstant.JWT_TOKEN_HEADER, "").build();
                serverWebExchange = serverWebExchange.mutate().request(request).build();
                return webFilterChain.filter(serverWebExchange);
            }
        }
        return webFilterChain.filter(serverWebExchange);
    }
}
