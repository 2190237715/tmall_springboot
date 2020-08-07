package com.xiaoxin.demo.controller;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.service.CategoryService;
import com.xiaoxin.demo.util.ImageUtil;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName CategoryController--类别管理控制器（RESTFUL方式）
 * @createDate 2019/11/5 19:40
 */
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 新增类别
     *
     * @param category--类别实体
     * @param image--图片
     * @param request
     * @return 类别实体
     * @throws IOException
     */
    @PostMapping("/categories")
    public Category addCategory(Category category, MultipartFile image, HttpServletRequest request) throws IOException {
        categoryService.addCategory(category);
        saveOrUpdateImageFile(category, image, request);
        return category;
    }

    /**
     * 根据ID删除类别
     *
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping("/categories/{id}")
    public String deleteCategoryById(@PathVariable("id") int id, HttpServletRequest request) {
        categoryService.deleteCategoryById(id);
        File file = new File(request.getServletContext().getRealPath("img/category"));
        file.delete();
        return null;
    }


    /**
     * 修改类别
     *
     * @param category
     * @param image
     * @param request
     * @return
     * @throws IOException
     */
    @PutMapping("/categories/{id}")
    public Category editCategory(Category category, MultipartFile image, HttpServletRequest request) throws IOException {
        String name = request.getParameter("name");
        category.setName(name);
        categoryService.editCategory(category);
        if (null != image) {
            saveOrUpdateImageFile(category, image, request);
        }
        return category;
    }

    /**
     * 根据ID查询类别实体
     *
     * @param id
     * @return
     */
    @GetMapping("/categories/{id}")
    public Category findCategoryById(@PathVariable("id") int id) {
        Category category = categoryService.findCategoryById(id);
        return category;
    }

    /**
     * 查询非分页的总列表
     *
     * @return 返回类别列表--暂时不用
     */
    @GetMapping("/categories/nopage")
    public List<Category> list() {
        List<Category> categories = categoryService.list();
        return categories;
    }

    /**
     * 分页查询
     *
     * @param start--当前页数
     * @param size--显示条数
     * @return 返回类别分页列表
     */
    @GetMapping("/categories")
    public Page4Navigator<Category> categorylist(@RequestParam(value = "start", defaultValue = "0") int start,
                                                 @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        //navigatePages:导航栏数量（暂定为5）
        Page4Navigator<Category> page = categoryService.categoryList(start, size, 5);
        return page;
    }

    /**
     * 图片转换--jpg
     *
     * @param category
     * @param image
     * @param request
     * @throws IOException
     */
    public void saveOrUpdateImageFile(Category category, MultipartFile image, HttpServletRequest request) throws IOException {
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, category.getId() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }

}
