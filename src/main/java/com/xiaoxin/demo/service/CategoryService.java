package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.util.Page4Navigator;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName categoryService
 * @createDate 2019/11/5 17:11
 */
public interface CategoryService {

    /**
     * 新增
     *
     * @param category--类别实体
     */
    public void addCategory(Category category);

    /**
     * 删除
     *
     * @param id--类别ID
     */
    public void deleteCategoryById(int id);


    /**
     * 修改
     *
     * @param category--类别实体
     */
    public void editCategory(Category category);

    /**
     * 根据ID查询Category
     *
     * @param id--类别ID
     * @return
     */
    public Category findCategoryById(int id);

    /**
     * 查询列表
     *
     * @return
     */
    public List<Category> list();

    /**
     * 分页查询
     *
     * @param start--当前页码（默认为0）
     * @param size--每页显示最大页数（默认为5）
     * @param navigatePages
     * @return
     */
    public Page4Navigator<Category> categoryList(int start, int size, int navigatePages);

}
