package com.wen.sai.service.impl;

import com.wen.sai.model.Product;
import com.wen.sai.mapper.ProductMapper;
import com.wen.sai.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2021-02-28
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
