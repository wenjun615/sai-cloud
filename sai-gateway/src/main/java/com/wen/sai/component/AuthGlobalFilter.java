package com.wen.sai.component;

import cn.hutool.core.util.StrUtil;
import com.nimbusds.jose.JWSObject;
import com.wen.sai.common.domain.constant.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * <p>
 * 将 JWT 中存储的用户信息存储到 Header 中去的全局过滤器
 * </p>
 *
 * @author wenjun
 * @since 2021/2/22
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        if (StrUtil.isBlank(token)) {
            return chain.filter(exchange);
        }
        try {
            String relToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(relToken);
            String user = jwsObject.getPayload().toString();
            log.info("AuthGlobalFilter user:{}", user);
            request = request.mutate().header(AuthConstant.USER_HEADER, user).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            log.warn(e.getMessage(), e);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
