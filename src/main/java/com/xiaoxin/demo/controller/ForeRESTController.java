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

import javax.servlet.http.HttpSession;
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
     * @param user 用户实体
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

    /**
     * 登陆
     *
     * @param userParam 前台传的用户实体
     * @param session   会画，用于存取用户实体
     * @return
     */
    @PostMapping("/forelogin")
    public Result login(@RequestBody User userParam, HttpSession session) {
        String name = HtmlUtils.htmlEscape(userParam.getName());
        User user = userService.login(name, userParam.getPassword());
        if (null == user) {
            String message = "账号密码错误，请重新输入！";
            return Result.fail(message);
        }
        session.setAttribute("user", user);
        return Result.success(user);
    }
}
