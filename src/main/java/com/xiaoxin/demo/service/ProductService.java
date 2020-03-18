package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.util.Page4Navigator;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ProductService
 * @createDate 2019/11/19 15:00
 */
public interface ProductService {
    /**
     * 新增属性
     *
     * @param product
     */
    void addProduct(Product product);

    /**
     * 删除属性
     *
     * @param id
     */
    void deleteProductById(int id);

    /**
     * 修改属性
     *
     * @param product
     */
    void editProduct(Product product);

    /**
     * 根据ID查询属性
     *
     * @param id
     * @return
     */
    Product findProductById(int id);

    /**
     * 基于Category查询产品分页列表
     *
     * @param cid--Category
     * @param start
     * @param size
     * @param navigatePages
     * @return
     */
    Page4Navigator<Product> ProductList(int cid, int start, int size, int navigatePages);

    /**
     * 基于Categor按照ID排序查询产品列表
     *
     * @param category
     * @return
     */
    List<Product> listByCategory(Category category);

    /**
     * 为分类填充产品集合
     *
     * @param category
     */
    void fill(Category category);

    /**
     * 为多个分类填充产品集合
     *
     * @param categories
     */
    void fill(List<Category> categories);

    /**
     * 为多个分类填充推荐产品集合，即把分类下的产品集合，按照8个为一行，拆成多行，以利于后续页面上进行显示
     *
     * @param categories
     */
    void fillByRow(List<Category> categories);

    /**
     * 设置销量数量
     *
     * @param product
     */
    void setSaleAndReviewNumber(Product product);

    /**
     * 设置评价数量
     *
     * @param products
     */
    void setSaleAndReviewNumber(List<Product> products);
}
