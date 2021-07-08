package com.wen.sai.model;

import com.baomidou.mybatisplus.annotation.*;
import com.wen.sai.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author wenjun
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("resource")
@ApiModel(value = "Resource对象", description = "资源表")
public class Resource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "路径")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "是否删除")
    @TableField("is_deleted")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
