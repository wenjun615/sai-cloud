package com.wen.sai.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Swagger 资源配置
 * </p>
 *
 * @author wenjun
 * @since 2021/2/24
 */
@Configuration
@AllArgsConstructor
@Primary
public class SwaggerResourceConfig implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;

    private final GatewayProperties gatewayProperties;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> swaggerResourceList = new ArrayList<>();
        List<String> routeIdList = new ArrayList<>();
        // 获取所有路由 ID
        routeLocator.getRoutes()
                .subscribe(route -> routeIdList.add(route.getId()));
        // 根据路由生成其他服务的 api-docs 路径
        gatewayProperties.getRoutes()
                .stream()
                .filter(routeDefinition -> routeIdList.contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition.getPredicates()
                        .stream()
                        .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                        .forEach(predicateDefinition -> swaggerResourceList.add(swaggerResource(
                                routeDefinition.getId(),
                                predicateDefinition.getArgs()
                                        .get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                        .replace("**", "v3/api-docs")))));
        return swaggerResourceList;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("3.0");
        return swaggerResource;
    }
}
