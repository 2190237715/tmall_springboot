package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.util.Page4Navigator;

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
    public void addProduct(Product product);

    /**
     * 删除属性
     *
     * @param id
     */
    public void deleteProductById(int id);

    /**
     * 修改属性
     *
     * @param product
     */
    public void editProduct(Product product);

    /**
     * 根据ID查询属性
     *
     * @param id
     * @return
     */
    public Product findProductById(int id);

    /**
     * 基于Category查询产品分页列表
     *
     * @param cid--Category
     * @param start
     * @param size
     * @param navigatePages
     * @return
     */
    public Page4Navigator<Product> ProductList(int cid, int start, int size, int navigatePages);


}
