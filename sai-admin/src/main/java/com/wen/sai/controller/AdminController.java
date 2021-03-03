package com.wen.sai.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.wen.sai.common.api.CommonResult;
import com.wen.sai.common.domain.BaseController;
import com.wen.sai.common.domain.bo.AdminUserDetails;
import com.wen.sai.common.domain.dto.Oauth2TokenDTO;
import com.wen.sai.common.domain.dto.UserDTO;
import com.wen.sai.common.domain.query.AdminQuery;
import com.wen.sai.common.util.CommonUtils;
import com.wen.sai.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author wenjun
 * @since 2021-02-20
 */
@AllArgsConstructor
@RestController
@RequestMapping("/admin")
@Api(tags = {"AdminController"})
public class AdminController extends BaseController {

    private final AdminService adminService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @ApiOperation("加载登录用户信息")
    @GetMapping("/loadCurrentUser")
    public UserDTO loadCurrentUser() {
        HttpServletRequest request = CommonUtils.getRequest();
        String userStr = request.getHeader("user");
        JSONObject user = new JSONObject(userStr);
        return UserDTO.builder()
                .id(user.getStr("id"))
                .username(user.getStr("username"))
                .authorities(Convert.toList(String.class, user.get("authorities")))
                .build();
    }

    @ApiOperation("根据用户名加载登录用户信息")
    @GetMapping("/loadUserByUsername")
    public AdminUserDetails loadUserByUsername(@RequestParam String username) {
        return adminService.loadUserByUsername(username);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult<Oauth2TokenDTO> login(@Validated @RequestBody AdminQuery adminQuery) {
        return adminService.login(adminQuery);
    }
}
