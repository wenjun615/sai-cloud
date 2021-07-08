package com.wen.sai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wen.sai.model.Resource;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author wenjun
 * @since 2021-07-03
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 根据用户 ID 获取资源列表
     *
     * @param userId 用户 ID
     * @return 资源列表
     */
    List<Resource> listByUserId(String userId);
}
