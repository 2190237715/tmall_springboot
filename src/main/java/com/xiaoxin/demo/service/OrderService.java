package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Order;
import com.xiaoxin.demo.pojo.OrderItem;
import com.xiaoxin.demo.util.Page4Navigator;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName OrderService
 * @createDate 2019/11/25 18:27
 */
public interface OrderService {
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    Page4Navigator<Order> orderList(int start, int size, int navigatePages);

    void removeOrderFromOrderItem(List<Order> orders);

    void removeOrderFromOrderItem(Order order);

    Order findOrderById(int id);

    void updateOrder(Order order);

    float addOrder(Order order, List<OrderItem> orderItems);
}
