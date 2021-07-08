package com.wen.sai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.sai.common.api.CommonResult;
import com.wen.sai.common.entity.bo.UserDetailsBO;
import com.wen.sai.common.entity.query.UserQuery;
import com.wen.sai.common.entity.vo.UserVO;
import com.wen.sai.model.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wenjun
 * @since 2021-07-03
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名加载用户详情
     *
     * @param username 用户名
     * @return 用户详情
     */
    UserDetailsBO loadUserByUsername(String username);

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    User getByUsername(String username);

    /**
     * 登录
     *
     * @param query 请求参数
     * @return 返回结果
     */
    CommonResult<UserVO> login(UserQuery query);
}
