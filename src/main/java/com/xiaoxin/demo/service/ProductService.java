package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ProductService
 * @createDate 2019/11/19 15:00
 */
@CacheConfig(cacheNames = "products")
public interface ProductService {
    /**
     * 新增属性
     *
     * @param product
     */
    @CacheEvict(allEntries = true)
    void addProduct(Product product);

    /**
     * 删除属性
     *
     * @param id
     */
    @CacheEvict(allEntries = true)
    void deleteProductById(int id);

    /**
     * 修改属性
     *
     * @param product
     */
    @CacheEvict(allEntries = true)
    void editProduct(Product product);

    /**
     * 根据ID查询属性
     *
     * @param id
     * @return
     */
    @Cacheable(key = "'products-one-'+#p0")
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
    @Cacheable(key = "'products-cid-'+#p0+'-page-'+#p1+'-'+#p2")
    Page4Navigator<Product> ProductList(int cid, int start, int size, int navigatePages);

    /**
     * 基于Categor按照ID排序查询产品列表
     *
     * @param category
     * @return
     */
    @Cacheable(key = "'products-cid-'+#p0.id")
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

    /**
     * elasticsearch
     *
     * @param keyword
     * @param start
     * @param size
     * @return
     */
    List<Product> search(String keyword, int start, int size);

    /**
     * 根据名字进行模糊查询
     *
     * @param keyword
     * @param start
     * @param size
     * @return
     */
    List<Product> searchLike(String keyword, int start, int size);

    /**
     * 查询是否能删除分类
     *
     * @param cid
     * @return
     */
    int canDeleteCategory(int cid);
}
