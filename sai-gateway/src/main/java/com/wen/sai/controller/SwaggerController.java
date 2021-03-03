package com.wen.sai.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 自定义 Swagger 各个获取数据的接口
 * </p>
 *
 * @author wenjun
 * @since 2021/2/24
 */
@RestController
public class SwaggerController {

    private SecurityConfiguration securityConfiguration;

    private UiConfiguration uiConfiguration;

    private final SwaggerResourcesProvider swaggerResourcesProvider;

    public SwaggerController(SwaggerResourcesProvider swaggerResourcesProvider) {
        this.swaggerResourcesProvider = swaggerResourcesProvider;
    }

    public void setSecurityConfiguration(SecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    public void setUiConfiguration(UiConfiguration uiConfiguration) {
        this.uiConfiguration = uiConfiguration;
    }

    /**
     * Swagger 安全配置
     */
    @GetMapping("/swagger-resources/configuration/security")
    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()),
                HttpStatus.OK
        ));
    }

    /**
     * Swagger UI 配置
     */
    @GetMapping("/swagger-resources/configuration/ui")
    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()),
                HttpStatus.OK
        ));
    }

    /**
     * Swagger 资源配置，各个服务的 api-docs 信息
     */
    @GetMapping("/swagger-resources")
    public Mono<ResponseEntity<List<SwaggerResource>>> swaggerResources() {
        return Mono.just(new ResponseEntity<>(swaggerResourcesProvider.get(), HttpStatus.OK));
    }
}
