package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.OrderItem;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.ProductImage;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ProductImageService
 * @createDate 2019/11/19 16:38
 */
@CacheConfig(cacheNames = "productImages")
public interface ProductImageService {

    String type_single = "single";
    String type_detail = "detail";


    /**
     * 新增产品图片
     *
     * @param productImage
     */
    @CacheEvict(allEntries = true)
    void addProductImage(ProductImage productImage);

    /**
     * 根据ID删除产品图片
     *
     * @param id
     */
    @CacheEvict(allEntries = true)
    void deleteProductImageById(int id);

    /**
     * 根据ID查询产品图片
     *
     * @param id
     * @return
     */
    @Cacheable(key = "'productImages-one-'+ #p0")
    ProductImage findProductImageById(int id);

    /**
     * 单个产品图片列表
     *
     * @param product
     * @return
     */
    @Cacheable(key = "'productImages-single-pid-'+ #p0.id")
    List<ProductImage> listSingleProductImages(Product product);

    /**
     * 详细产品图片列表
     *
     * @param product
     * @return
     */
    @Cacheable(key = "'productImages-detail-pid-'+ #p0.id")
    List<ProductImage> listDetailProductImages(Product product);

    /**
     * 设置产品图片
     *
     * @param product
     */
    void setFirstProductImage(Product product);

    /**
     * 设置产品图片列表
     *
     * @param products
     */
    void setFirstProductImages(List<Product> products);

    /**
     * 将订单详情设置第一张图片
     *
     * @param orderItems
     */
    void setFirstProductImagesOnOrderItems(List<OrderItem> orderItems);
}
