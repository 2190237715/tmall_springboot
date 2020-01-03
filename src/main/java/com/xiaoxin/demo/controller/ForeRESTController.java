package com.xiaoxin.demo.controller;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.User;
import com.xiaoxin.demo.service.CategoryService;
import com.xiaoxin.demo.service.ProductService;
import com.xiaoxin.demo.service.UserService;
import com.xiaoxin.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import org.unbescape.html.HtmlEscape;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ForeRESTController--前台控制器
 * @createDate 2019/12/17 10:32
 */
@RestController
public class ForeRESTController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    /**
     * 填充产品清除重复类别
     *
     * @return
     */
    @GetMapping("/forehome")
    public List<Category> home() {
        List<Category> categories = categoryService.list();
        productService.fill(categories);
        productService.fillByRow(categories);
        categoryService.removeCategoryFromProduct(categories);
        return categories;
    }


    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("/foreregister")
    public Result register(@RequestBody User user) {
        String name = user.getName();
        String password = user.getPassword();
        boolean exist = userService.isExist(name);
        if (exist) {
            String message = "用户名已被占用，请重新输入！";
            return Result.fail(message);
        }
        user.setName(HtmlUtils.htmlEscape(name));
        user.setPassword(password);
        userService.addUser(user);
        return Result.success();
    }
}
