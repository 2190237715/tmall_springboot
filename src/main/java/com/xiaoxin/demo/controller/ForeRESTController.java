package com.xiaoxin.demo.controller;

import com.xiaoxin.demo.comparator.*;
import com.xiaoxin.demo.pojo.*;
import com.xiaoxin.demo.service.*;
import com.xiaoxin.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.*;

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
    @Autowired
    ReviewService reviewService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;

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
     * @param session   会话，用于存取用户实体
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

    @GetMapping("/foreproduct/{pid}")
    public Object product(@PathVariable("pid") int pid) {
        Product product = productService.findProductById(pid);
        List<ProductImage> productSingleImages = productImageService.listSingleProductImages(product);
        List<ProductImage> productDetailImages = productImageService.listDetailProductImages(product);
        product.setProductSingleImages(productSingleImages);
        product.setProductDetailImages(productDetailImages);

        List<PropertyValue> propertyValues = propertyValueService.propertyValueList(product);
        List<Review> reviews = reviewService.reviewList(product);
        productService.setSaleAndReviewNumber(product);
        productImageService.setFirstProductImage(product);

        Map<String, Object> map = new HashMap<>();
        map.put("product", product);
        map.put("pvs", propertyValues);
        map.put("reviews", reviews);
        return Result.success(map);
    }

    /**
     * 获取session里的user 判断是否为空 即判断是否登陆
     *
     * @param session
     * @return
     */
    @GetMapping("forecheckLogin")
    public Object checkLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (null != user) {
            return Result.success();
        }
        return Result.fail("未登陆");
    }

    @GetMapping("forecategory/{cid}")
    public Object category(@PathVariable("cid") int cid, String sort) {
        Category category = categoryService.findCategoryById(cid);
        productService.fill(category);
        productService.setSaleAndReviewNumber(category.getProducts());
        categoryService.removeCategoryFromProduct(category);
        if (null != sort) {
            switch (sort) {
                case "all":
                    Collections.sort(category.getProducts(), new ProductAllComparator());
                    break;
                case "date":
                    Collections.sort(category.getProducts(), new ProductDateComparator());
                    break;
                case "price":
                    Collections.sort(category.getProducts(), new ProductPriceComparator());
                    break;
                case "reciew":
                    Collections.sort(category.getProducts(), new ProductReviewComparator());
                    break;
                case "saleCount":
                    Collections.sort(category.getProducts(), new ProductSaleCountComparator());
                    break;
            }
        }
        return category;
    }


    @PostMapping("foresearch")
    public Object search(String keyword) {
        if (null == keyword) {
            keyword = "";
        }
        List<Product> products = productService.search(keyword, 0, 20);
        productImageService.setFirstProductImages(products);
        productService.setSaleAndReviewNumber(products);
        return products;
    }

}
