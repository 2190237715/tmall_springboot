package com.xiaoxin.demo.dao;

import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ProductImageDao
 * @createDate 2019/11/19 16:11
 */
@Repository
public interface ProductImageDao extends JpaRepository<ProductImage, Integer> {

    /**
     * 根据不同类型（单个/详细）和产品查询产品图片并降序排序
     *
     * @param product--产品
     * @param type--类型
     * @return
     */
    List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type);
}
