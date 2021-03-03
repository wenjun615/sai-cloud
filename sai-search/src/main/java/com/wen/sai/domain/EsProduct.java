package com.wen.sai.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * <p>
 * Es 商品信息
 * </p>
 *
 * @author wenjun
 * @since 2021/2/28
 */
@Document(indexName = "pms")
@Data
public class EsProduct implements Serializable {

    private static final long serialVersionUID = -8604294414715551116L;

    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 商品名称
     */
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String name;

    /**
     * 品牌名称
     */
    @Field(type = FieldType.Keyword)
    private String brandName;

    /**
     * 类别名称
     */
    @Field(type = FieldType.Keyword)
    private String categoryName;
}
