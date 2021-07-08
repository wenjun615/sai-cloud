package com.wen.sai.controller;

import com.wen.sai.common.api.CommonResult;
import com.wen.sai.common.entity.bo.UserDetailsBO;
import com.wen.sai.common.entity.query.UserQuery;
import com.wen.sai.common.entity.vo.UserVO;
import com.wen.sai.entity.base.BaseController;
import com.wen.sai.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wenjun
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Api(tags = {"UserController"})
public class UserController extends BaseController {

    private final UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult<UserVO> login(@RequestBody @Validated(UserQuery.Login.class) UserQuery query) {
        return userService.login(query);
    }

    @ApiOperation("根据用户名加载用户")
    @GetMapping("/loadUserByUsername")
    public UserDetailsBO loadUserByUsername(@RequestParam String username) {
        return userService.loadUserByUsername(username);
    }
}
