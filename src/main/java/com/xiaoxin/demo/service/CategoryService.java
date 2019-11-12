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
    public Page4Navigator<Category> list(int start, int size, int navigatePages);

    /**
     * 新增
     *
     * @param category--分类实体
     */
    public void add(Category category);
}
