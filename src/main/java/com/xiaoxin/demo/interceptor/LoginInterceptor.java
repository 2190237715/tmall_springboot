package com.xiaoxin.demo.interceptor;

import com.xiaoxin.demo.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author fuqiangxin
 * @Title: 登陆拦截器
 * @Description: 拦截需要登陆的页面
 * @date 2020/3/2110:45
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        HttpSession session = httpServletRequest.getSession();//获取会话
        String contextPath = session.getServletContext().getContextPath();//获取上下文路径
        String[] requireAuthPages = {
                "buy", "alipay", "payed", "cart", "bought", "confirmPay", "orderConfirmed",
                "forebuyone", "forebuy", "foreaddCart", "forecart", "forechangeOrderItem",
                "foredeleteOrderItem", "forecreateOrder", "forepayed", "forebought",
                "foreconfirmPay", "foreorderConfirmed", "foredeleteOrder", "forereview", "foredoreview"};
        String uri = httpServletRequest.getRequestURI();//获取完整路径
        uri = StringUtils.remove(uri, contextPath + "/");//截取关键跳转路径
        String page = uri;
        if (begingWith(page, requireAuthPages)) {
            User user = (User) session.getAttribute("user");
            if (null == user) {
                httpServletResponse.sendRedirect("login");
                return false;
            }
        }
        return true;
    }


    /**
     * 判断地址是否以字符数组中相符合
     *
     * @param page             地址中的字符串
     * @param requireAuthPages 数组中的字符串
     * @return
     */
    private boolean begingWith(String page, String[] requireAuthPages) {
        boolean result = false;
        for (String requireAuthPage : requireAuthPages
        ) {
            if (StringUtils.startsWith(page, requireAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
