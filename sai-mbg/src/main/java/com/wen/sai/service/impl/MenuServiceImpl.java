package com.wen.sai.service.impl;

import com.wen.sai.model.Menu;
import com.wen.sai.mapper.MenuMapper;
import com.wen.sai.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2021-02-20
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
