package com.wen.sai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.sai.common.api.CommonResult;
import com.wen.sai.common.domain.bo.AdminUserDetails;
import com.wen.sai.common.domain.dto.Oauth2TokenDTO;
import com.wen.sai.common.domain.query.AdminQuery;
import com.wen.sai.model.Admin;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author wenjun
 * @since 2021-02-20
 */
public interface AdminService extends IService<Admin> {

    /**
     * 根据用户名加载登录用户信息
     *
     * @param username 用户名
     * @return 登录用户信息
     */
    AdminUserDetails loadUserByUsername(String username);

    /**
     * 根据用户名获取后台用户信息
     *
     * @param username 用户名
     * @return 后台用户信息
     */
    Admin getByUsername(String username);

    /**
     * 登录
     *
     * @param adminQuery 请求参数
     * @return 返回结果
     */
    CommonResult<Oauth2TokenDTO> login(AdminQuery adminQuery);
}
