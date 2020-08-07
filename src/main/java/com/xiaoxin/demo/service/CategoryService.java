package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName categoryService
 * @createDate 2019/11/5 17:11
 */
@CacheConfig(cacheNames = "categories")
public interface CategoryService {

    /**
     * 新增
     *
     * @param category--类别实体
     */
    @CacheEvict(allEntries = true)
    void addCategory(Category category);

    /**
     * 删除
     *
     * @param id--类别ID
     */
    @CacheEvict(allEntries = true)
    void deleteCategoryById(int id);


    /**
     * 修改
     *
     * @param category--类别实体
     */
    @CacheEvict(allEntries = true)
    void editCategory(Category category);

    /**
     * 根据ID查询Category
     *
     * @param id--类别ID
     * @return
     */
    @Cacheable(key = "'categories-one-'+#p0")
    Category findCategoryById(int id);

    /**
     * 查询列表
     *
     * @return
     */
    @Cacheable(key = "'categories-all'")
    List<Category> list();

    /**
     * 分页查询
     *
     * @param start--当前页码（默认为0）
     * @param size--每页显示最大页数（默认为5）
     * @param navigatePages
     * @return
     */
    Page4Navigator<Category> categoryList(int start, int size, int navigatePages);

    /**
     * 删除产品上的分类   防止套娃
     *
     * @param category
     */
    @Cacheable(key = "'categories-page-'+#p0+'-'+#p1")
    void removeCategoryFromProduct(Category category);

    /**
     * 删除产品上的分类集合
     *
     * @param categories
     */
    void removeCategoryFromProduct(List<Category> categories);
}
