package com.wen.sai.service.impl;

import com.wen.sai.model.Role;
import com.wen.sai.mapper.RoleMapper;
import com.wen.sai.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台角色表 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2021-02-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
