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

    void fill(List<Order> orders);

    void fill(Order order);

    List<OrderItem> findByOrder(Order order);

    int getSaleCount(Product product);

    /**
     * 暂时没用到  排序的第二种方案
     *
     * @param order
     * @return
     */
    List<OrderItem> findByOrderOrderByIdDesc(Order order);

    List<OrderItem> findByProduct(Product product);

    List<OrderItem> listByUser(User user);

    void updateOrderItem(OrderItem orderItem);

    void addOrderItem(OrderItem orderItem);

    OrderItem findOrderItemById(int oiid);

    void deleteOrderItem(int oiid);

}
