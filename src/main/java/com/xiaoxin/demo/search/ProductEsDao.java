package com.xiaoxin.demo.search;

import com.xiaoxin.demo.pojo.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fuqiangxin
 * @Classname: ProductEsDao
 * @Description: 产品全局搜索
 * @date 2020/8/12 08:25
 */
@Repository
public interface ProductEsDao extends ElasticsearchRepository<Product, Integer> {
}
