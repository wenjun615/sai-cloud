package com.wen.sai.common.domain;

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * Swagger 配置属性
 * </p>
 *
 * @author wenjun
 * @since 2021/1/25
 */
@Data
@Builder(toBuilder = true)
public class SwaggerProperty {

    /**
     * 从该包下生成 API 文档
     */
    private String apiBasePackage;

    /**
     * 文档标题
     */
    private String title;

    /**
     * 文档描述
     */
    private String description;

    /**
     * 文档版本
     */
    private String version;

    /**
     * 文档联系人姓名
     */
    private String contactName;

    /**
     * 文档联系人网址
     */
    private String contactUrl;

    /**
     * 文档联系人邮箱
     */
    private String contactEmail;
}
