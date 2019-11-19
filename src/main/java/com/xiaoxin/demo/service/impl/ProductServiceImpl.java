package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.CategoryDao;
import com.xiaoxin.demo.dao.ProductDao;
import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.service.ProductService;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ProductServiceImpl
 * @createDate 2019/11/19 15:07
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;
    @Autowired
    CategoryDao categoryDao;

    @Override
    public void addProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public void deleteProductById(int id) {
        productDao.deleteById(id);
    }

    @Override
    public void editProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public Product findProductById(int id) {
        return productDao.findById(id).get();
    }

    @Override
    public Page4Navigator<Product> ProductList(int cid, int start, int size, int navigatePages) {
        Category category = categoryDao.findById(cid).get();
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Product> pageFromJPA = productDao.findByCategory(category, pageable);
        return new Page4Navigator(pageFromJPA, navigatePages);
    }
}
