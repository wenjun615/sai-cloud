package com.wen.sai.component;

import com.wen.sai.common.constant.AuthConstants;
import com.wen.sai.config.IgnoreProperties;
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
 * 白名单路径请求过滤器
 * </p>
 *
 * @author wenjun
 * @since 2021/2/22
 */
@Component
@AllArgsConstructor
public class IgnoreFilter implements WebFilter {

    private final IgnoreProperties ignoreProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        URI uri = request.getURI();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String pattern : ignoreProperties.getUrls()) {
            if (pathMatcher.match(pattern, uri.getPath())) {
                // 白名单路径请求移除 Authorization 请求头
                request = request.mutate()
                        .header(AuthConstants.JWT_TOKEN_HEADER, "")
                        .build();
                serverWebExchange = serverWebExchange.mutate()
                        .request(request)
                        .build();
                return webFilterChain.filter(serverWebExchange);
            }
        }
        return webFilterChain.filter(serverWebExchange);
    }
}
