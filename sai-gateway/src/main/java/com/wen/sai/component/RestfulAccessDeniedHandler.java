package com.wen.sai.component;

import cn.hutool.json.JSONUtil;
import com.wen.sai.common.api.CommonCode;
import com.wen.sai.common.api.CommonResult;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 自定义返回结果：权限不足时
 * </p>
 *
 * @author wenjun
 * @since 2020/12/21
 */
@Component
public class RestfulAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, AccessDeniedException e) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        HttpHeaders headers = response.getHeaders();
        headers.setAccessControlAllowOrigin("*");
        headers.setCacheControl("no-cache");
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonStr = JSONUtil.toJsonStr(CommonResult.failed(CommonCode.UNAUTHORISED));
        DataBuffer buffer = response.bufferFactory().wrap(jsonStr.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
