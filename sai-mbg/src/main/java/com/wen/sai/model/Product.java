package com.wen.sai.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.wen.sai.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author wenjun
 * @since 2021-02-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pms_product")
@ApiModel(value="Product对象", description="商品信息表")
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "商品名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "品牌名称")
    @TableField("brandName")
    private String brandname;

    @ApiModelProperty(value = "类别名称")
    @TableField("categoryName")
    private String categoryname;


}
