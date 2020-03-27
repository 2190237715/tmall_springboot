package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Order;
import com.xiaoxin.demo.pojo.OrderItem;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.User;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName OrderItemService
 * @createDate 2019/11/25 18:38
 */
public interface OrderItemService {

    /**
     * 填充订单详情
     *
     * @param order
     */
    void fill(Order order);

    void fill(List<Order> orders);

    void addOrderItem(OrderItem orderItem);

    void deleteOrderItem(int oiid);

    void updateOrderItem(OrderItem orderItem);

    int getSaleCount(Product product);

    OrderItem findOrderItemById(int oiid);

    List<OrderItem> listByUser(User user);

    List<OrderItem> listByOrder(Order order);

    List<OrderItem> listByProduct(Product product);

}
