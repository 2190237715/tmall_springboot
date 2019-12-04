package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.ProductImage;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ProductImageService
 * @createDate 2019/11/19 16:38
 */
public interface ProductImageService {

    String type_single = "single";
    String type_detail = "detail";

    /**
     * 新增产品图片
     *
     * @param productImage
     */
    void addProductImage(ProductImage productImage);

    /**
     * 根据ID删除产品图片
     *
     * @param id
     */
    void deleteProductImageById(int id);

    /**
     * 根据ID查询产品图片
     *
     * @param id
     * @return
     */
    ProductImage findProductImageById(int id);

    /**
     * 单个产品图片列表
     *
     * @param product
     * @return
     */
    List<ProductImage> listSingleProductImages(Product product);

    /**
     * 详细产品图片列表
     *
     * @param product
     * @return
     */
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

}
