package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Category;

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


}
