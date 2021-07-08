package com.wen.sai.common.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * 用户数据传输对象
 * </p>
 *
 * @author wenjun
 * @since 2021-07-08
 */
@Data
public class UserDTO {

    private List<String> authorities;
}
