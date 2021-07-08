package com.wen.sai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <p>
 * 网关白名单路径配置
 * </p>
 *
 * @author wenjun
 * @since 2021/2/22
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "secure.ignore")
public class IgnoreProperties {

    private List<String> urls;
}
