package com.wen.sai.repository;

import com.wen.sai.domain.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <p>
 * 搜索商品 ES 操作类
 * </p>
 *
 * @author wenjun
 * @since 2021/2/28
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, String> {

    /**
     * 搜索
     *
     * @param name         商品名称
     * @param brandName    品牌名称
     * @param categoryName 类别名称
     * @return Page<EsProduct>
     */
    Page<EsProduct> findByNameOrBrandNameOrCategoryName(String name, String brandName, String categoryName);
}
