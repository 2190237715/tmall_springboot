package com.xiaoxin.demo.controller;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.service.CategoryService;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName CategoryController--RESTFUL服务器控制器
 * @createDate 2019/11/5 19:40
 */
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 查询非分页的总列表
     *
     * @return
     */
    @GetMapping("/categories/nopage")
    public List<Category> list() {
        List<Category> categories = categoryService.list();
        return categories;
    }

    /**
     * 分页查询
     * @param start--当前页数
     * @param size--显示条数
     * @return
     */
    @GetMapping("/categories")
    public Page4Navigator<Category> list(@RequestParam(value = "start", defaultValue = "0") int start,
                                         @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        //navigatePages:导航栏数量（暂定为5）
        Page4Navigator<Category> page = categoryService.list(start, size, 5);
        return page;
    }

}
