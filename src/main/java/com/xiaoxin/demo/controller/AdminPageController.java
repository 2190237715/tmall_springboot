package com.xiaoxin.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName AdminPageController--用于跳转
 * @createDate 2019/11/5 19:34
 */
@Controller
public class AdminPageController {
    @GetMapping(value = "admin")
    public String admin() {
        return "redirect:admin_category_list";
    }

    @GetMapping(value = "/admin_category_list")
    public String listCategory() {
        return "admin/listCategory";
    }


    @GetMapping(value = "/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping(value = "/admin_category_edit")
    public String editCategory() {
        return "admin/editCategory";
    }
}
