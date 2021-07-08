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
 * 用户和角色关系表
 * </p>
 *
 * @author wenjun
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_role_relation")
@ApiModel(value = "UserRoleRelation对象", description = "用户和角色关系表")
public class UserRoleRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private String roleId;

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
