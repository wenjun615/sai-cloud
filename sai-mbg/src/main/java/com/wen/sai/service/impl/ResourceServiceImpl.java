package com.wen.sai.service.impl;

import com.wen.sai.model.Resource;
import com.wen.sai.mapper.ResourceMapper;
import com.wen.sai.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台资源表 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2021-02-20
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}
