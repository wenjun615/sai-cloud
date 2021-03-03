package com.wen.sai.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import com.wen.sai.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台角色和资源关系表
 * </p>
 *
 * @author wenjun
 * @since 2021-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ums_role_resource_relation")
@ApiModel(value="RoleResourceRelation对象", description="后台角色和资源关系表")
public class RoleResourceRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "后台角色ID")
    @TableField("role_id")
    private String roleId;

    @ApiModelProperty(value = "后台资源ID")
    @TableField("resource_id")
    private String resourceId;

    @ApiModelProperty(value = "是否删除：1是，0否")
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
