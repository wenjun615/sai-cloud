package com.wen.sai.service;

import com.wen.sai.common.api.CommonResult;
import com.wen.sai.common.entity.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * <p>
 * 认证授权相关功能远程调用
 * </p>
 *
 * @author wenjun
 * @since 2021/2/23
 */
@FeignClient("sai-auth")
public interface AuthService {

    /**
     * 加载令牌
     *
     * @param paramMap 请求参数
     * @return 返回结果
     */
    @PostMapping("/oauth/token")
    CommonResult<UserVO> loadToken(@RequestParam Map<String, String> paramMap);
}
