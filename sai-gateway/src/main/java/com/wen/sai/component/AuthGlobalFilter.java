package com.wen.sai.component;

import cn.hutool.core.util.StrUtil;
import com.nimbusds.jose.JWSObject;
import com.wen.sai.common.constant.AuthConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.Objects;

/**
 * <p>
 * 认证授权全局过滤器
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
        String authHeader = request.getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);
        if (StrUtil.isNotBlank(authHeader) && authHeader.startsWith(AuthConstants.JWT_TOKEN_PREFIX)) {
            String token = authHeader.substring(AuthConstants.JWT_TOKEN_PREFIX.length());
            JWSObject jwsObject;
            try {
                jwsObject = JWSObject.parse(token);
            } catch (ParseException e) {
                log.warn("令牌校验异常：{}", e.getMessage(), e);
                jwsObject = null;
            }
            if (Objects.nonNull(jwsObject)) {
                String user = jwsObject.getPayload().toString();
                request = request.mutate()
                        .header(AuthConstants.USER_HEADER, user)
                        .header(AuthConstants.JWT_TOKEN_HEADER, "")
                        .build();
                exchange = exchange.mutate()
                        .request(request)
                        .build();
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
