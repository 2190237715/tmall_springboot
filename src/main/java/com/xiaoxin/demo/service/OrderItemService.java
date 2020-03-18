package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Order;
import com.xiaoxin.demo.pojo.OrderItem;
import com.xiaoxin.demo.pojo.Product;
import org.springframework.stereotype.Service;

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

    List<OrderItem> findByOrderOrderByIdDesc(Order order);

    List<OrderItem> findByProduct(Product product);

}
