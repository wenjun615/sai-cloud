package com.wen.sai.service;

import com.wen.sai.common.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * <p>
 * 资源相关功能远程调用
 * </p>
 *
 * @author wenjun
 * @since 2021/2/23
 */
@FeignClient("sai-admin")
public interface ResourceService {

    /**
     * 加载资源与权限对应关系
     *
     * @return 资源与权限对应关系
     */
    @GetMapping("/resource/loadAuthorityMap")
    CommonResult<Map<Object, Object>> loadAuthorityMap();
}
