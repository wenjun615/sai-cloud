package com.wen.sai.controller;

import com.wen.sai.common.api.CommonResult;
import com.wen.sai.entity.base.BaseController;
import com.wen.sai.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 *
 * @author wenjun
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/resource")
@AllArgsConstructor
public class ResourceController extends BaseController {

    private final ResourceService resourceService;

    /**
     * 加载资源与权限对应关系
     *
     * @return 资源与权限对应关系
     */
    @GetMapping("/loadAuthorityMap")
    public CommonResult<Map<Object, Object>> loadAuthorityMap() {
        Map<Object, Object> authorityMap = resourceService.getAuthorityMap();
        return CommonResult.successful(authorityMap);
    }
}
