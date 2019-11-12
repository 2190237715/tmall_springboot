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
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
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
     *
     * @param start--当前页数
     * @param size--显示条数
     * @return
     */
    @GetMapping("/categories")
    public Page4Navigator<Category> list(@RequestParam(value = "start", defaultValue = "0") int start,
                                         @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        //navigatePages:导航栏数量（暂定为5）
        Page4Navigator<Category> page = categoryService.list(start, 5, 5);
        return page;
    }

    @PostMapping("/categories")
    public Object add(Category category, MultipartFile image, HttpServletRequest request) throws IOException {
        categoryService.add(category);
        saveOrUpdateImageFile(category, image, request);
        return category;
    }

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
