package com.xiaoxin.demo.interceptor;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.OrderItem;
import com.xiaoxin.demo.pojo.User;
import com.xiaoxin.demo.service.CategoryService;
import com.xiaoxin.demo.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author fuqiangxin
 * @Title: 其他拦截器
 * @Description: 主要用于点logo可以返回首页，搜索栏下面显示分类，购物车显示该用户的购物数量
 * @date 2020/3/2309:20
 */
public class OtherInterceptor implements HandlerInterceptor {

    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int cartTotalItemNumber = 0;
        if (null != user) {
            List<OrderItem> orderItems = orderItemService.listByUser(user);
            for (OrderItem orderItem : orderItems
            ) {
                cartTotalItemNumber += orderItem.getNumber();
            }
        }
        List<Category> categories = categoryService.list();
        String contextPath = request.getServletContext().getContextPath();
        request.getServletContext().setAttribute("categories_below_search", categories);
        request.getServletContext().setAttribute("contextPath", contextPath);
        session.setAttribute("cartTotalItemNumber", cartTotalItemNumber);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
