package com.wen.sai.model;

import com.baomidou.mybatisplus.annotation.*;
import com.wen.sai.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * <p>
 * 后台用户表
 * </p>
 *
 * @author wenjun
 * @since 2021-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_admin")
@ApiModel(value = "Admin对象", description = "后台用户表")
public class Admin extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "头像")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "备注信息")
    @TableField("note")
    private String note;

    @ApiModelProperty(value = "最后登录时间")
    @TableField("login_time")
    private Date loginTime;

    @ApiModelProperty(value = "状态：1启用，0禁用")
    @TableField("status")
    private Integer status;

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
