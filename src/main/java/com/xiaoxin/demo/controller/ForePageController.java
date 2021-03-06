package com.xiaoxin.demo.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ForePageController--前台跳转管理控制器
 * @createDate 2019/12/17 10:12
 */
@Controller
@Api(tags = "用于前台跳转管理")
public class ForePageController {
    @GetMapping(value = "/")
    public String index() {
        return "redirect:home";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "fore/home";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "fore/register";
    }

    @GetMapping(value = "/alipay")
    public String alipay() {
        return "fore/alipay";
    }

    @GetMapping(value = "/bought")
    public String bought() {
        return "fore/bought";
    }

    @GetMapping(value = "/buy")
    public String buy() {
        return "fore/buy";
    }

    @GetMapping(value = "/cart")
    public String cart() {
        return "fore/cart";
    }

    @GetMapping(value = "/category")
    public String category() {
        return "fore/category";
    }

    @GetMapping(value = "/confirmPay")
    public String confirmPay() {
        return "fore/confirmPay";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "fore/login";
    }

    @GetMapping(value = "/orderConfirmed")
    public String orderConfirmed() {
        return "fore/orderConfirmed";
    }

    @GetMapping(value = "/payed")
    public String payed() {
        return "fore/payed";
    }

    /**
     * 产品
     *
     * @return
     */
    @GetMapping(value = "/product")
    public String product() {
        return "fore/product";
    }

    /**
     * 注册成功
     *
     * @return
     */
    @GetMapping(value = "/registerSuccess")
    public String registerSuccess() {
        return "fore/registerSuccess";
    }

    /**
     * 评价
     *
     * @return
     */
    @GetMapping(value = "/review")
    public String review() {
        return "fore/review";
    }

    /**
     * 搜索
     *
     * @return
     */
    @GetMapping(value = "/search")
    public String searchResult() {
        return "fore/search";
    }

    /**
     * 退出 清除session里的user值
     *
     * @param session
     * @return
     */
    @GetMapping("/forelogout")
    public String logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "redirect:home";
    }
}
