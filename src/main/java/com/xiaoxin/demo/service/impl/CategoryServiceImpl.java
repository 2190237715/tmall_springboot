package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.CategoryDao;
import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.service.CategoryService;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName CategoryServiceImpl--类别业务实现类
 * @createDate 2019/11/5 17:22
 */
@Service
@CacheConfig(cacheNames = "categories")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;


    @Override
    @CacheEvict(allEntries = true)
    public void addCategory(Category category) {
        categoryDao.save(category);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void deleteCategoryById(int id) {
        categoryDao.deleteById(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void editCategory(Category category) {
        categoryDao.save(category);
    }

    @Override
    @Cacheable(key = "'categories-one-'+#p0")
    public Category findCategoryById(int id) {
        return categoryDao.findById(id).get();
    }

    @Override
    @Cacheable(key = "'categories-all'")
    public List<Category> list() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return categoryDao.findAll(sort);
    }

    @Override
    @Cacheable(key = "'categories-page-'+#p0+'-'+#p1")
    public Page4Navigator<Category> categoryList(int start, int size, int navigatePages) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Category> pageFromJPA = categoryDao.findAll(pageable);
        return new Page4Navigator(pageFromJPA, navigatePages);
    }

    @Override
    public void removeCategoryFromProduct(Category category) {
        List<Product> products = category.getProducts();
        if (null != products) {
            for (Product product : products) {
                product.setCategory(null);
            }
        }
        List<List<Product>> productsByRows = category.getProductsByRow();
        if (null != productsByRows) {
            for (List<Product> productList : productsByRows) {
                for (Product product : productList) {
                    product.setCategory(null);
                }
            }
        }
    }

    @Override
    public void removeCategoryFromProduct(List<Category> categories) {
        for (Category category : categories) {
            removeCategoryFromProduct(category);
        }
    }


}
