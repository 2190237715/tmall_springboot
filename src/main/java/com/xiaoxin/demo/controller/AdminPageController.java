package com.xiaoxin.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName AdminPageController--后台跳转管理控制器
 * @createDate 2019/11/5 19:34
 */
@Controller
public class AdminPageController {


    @GetMapping(value = "admin")
    public String admin() {
        return "redirect:admin_category_list";
    }

    /**
     * 跳转分类首页
     *
     * @return
     */
    @GetMapping(value = "/admin_category_list")
    public String listCategory() {
        return "admin/listCategory";
    }

    /**
     * 跳转分类编辑页面
     *
     * @return
     */
    @GetMapping(value = "/admin_category_edit")
    public String editCategory() {
        return "admin/editCategory";
    }

    /**
     * 跳转属性首页
     *
     * @return
     */
    @GetMapping(value = "/admin_property_list")
    public String listProperty() {
        return "admin/listProperty";
    }

    /**
     * 跳转属性编辑页面
     *
     * @return
     */
    @GetMapping(value = "admin_property_edit")
    public String editProperty() {
        return "admin/editProperty";
    }

    /**
     * 跳转产品首页
     *
     * @return
     */
    @GetMapping(value = "/admin_product_list")
    public String listProduct() {
        return "admin/listProduct";
    }

    /**
     * 跳转产品编辑页面
     *
     * @return
     */
    @GetMapping(value = "admin_product_edit")
    public String editProduct() {
        return "admin/editProduct";
    }

    /**
     * 跳转产品图片首页
     *
     * @return
     */
    @GetMapping(value = "/admin_productImage_list")
    public String listProductImage() {
        return "admin/listProductImage";
    }

    /**
     * 跳转产品属性编辑页面
     *
     * @return
     */
    @GetMapping(value = "admin_propertyValue_edit")
    public String editPropertyValue() {
        return "admin/editPropertyValue";
    }

    /**
     * 跳转用户首页
     *
     * @return
     */
    @GetMapping(value = "admin_user_list")
    public String listUser() {
        return "admin/listUser";
    }

    /**
     * 跳转订单首页
     *
     * @return
     */
    @GetMapping(value = "admin_order_list")
    public String listOrder() {
        return "admin/listOrder";
    }

    /**
     * 跳转测试页面
     *
     * @return
     */
    @GetMapping(value = "/index")
    public String index() {
        return "admin/index";
    }
}
