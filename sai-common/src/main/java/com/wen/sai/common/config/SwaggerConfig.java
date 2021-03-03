package com.wen.sai.common.config;

import com.wen.sai.common.domain.SwaggerProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Swagger 配置
 * </p>
 *
 * @author wenjun
 * @since 2021/1/25
 */
@Configuration
public class SwaggerConfig {

    /**
     * 创建 API 应用
     *
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        SwaggerProperty swaggerProperty = swaggerProperty();
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo(swaggerProperty))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperty.getApiBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * Swagger 配置属性
     *
     * @return Swagger 配置属性
     */
    private SwaggerProperty swaggerProperty() {
        return SwaggerProperty.builder()
                .apiBasePackage("com.wen.sai.controller")
                .title("Sai Cloud API 文档")
                .description("Sai Cloud API 文档")
                .version("1.0.0")
                .contactName("wenjun")
                .contactUrl("")
                .contactEmail("835045686@qq.com")
                .build();
    }

    /**
     * API 文档基本信息
     *
     * @param swaggerProperty Swagger 配置属性
     * @return ApiInfo
     */
    private ApiInfo apiInfo(SwaggerProperty swaggerProperty) {
        return new ApiInfoBuilder()
                .title(swaggerProperty.getTitle())
                .description(swaggerProperty.getDescription())
                .version(swaggerProperty.getVersion())
                .contact(new Contact(
                        swaggerProperty.getContactName(),
                        swaggerProperty.getContactUrl(),
                        swaggerProperty.getContactEmail()))
                .build();
    }

    /**
     * 配置授权信息
     *
     * @return List<SecurityScheme>
     */
    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(new ApiKey(
                "Authorization",
                "token",
                io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER.toString()));
    }

    /**
     * 授权信息全局应用
     *
     * @return List<SecurityContext>
     */
    private List<SecurityContext> securityContexts() {
        AuthorizationScope[] scopes = {new AuthorizationScope("global", "accessEverything")};
        SecurityReference reference = new SecurityReference("Authorization", scopes);
        SecurityContext context = SecurityContext.builder()
                .securityReferences(Collections.singletonList(reference))
                .build();
        return Collections.singletonList(context);
    }
}
