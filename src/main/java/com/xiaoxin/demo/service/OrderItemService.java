package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Order;
import com.xiaoxin.demo.pojo.OrderItem;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName OrderItemService
 * @createDate 2019/11/25 18:38
 */
@CacheConfig(cacheNames = "orderItems")
public interface OrderItemService {

    /**
     * 填充订单详情
     *
     * @param order
     */
    void fill(Order order);

    void fill(List<Order> orders);

    @CacheEvict(allEntries = true)
    void addOrderItem(OrderItem orderItem);

    @CacheEvict(allEntries = true)
    void deleteOrderItem(int oiid);

    @CacheEvict(allEntries = true)
    void updateOrderItem(OrderItem orderItem);

    int getSaleCount(Product product);

    @Cacheable(key = "'orderItems-one-'+ #p0")
    OrderItem findOrderItemById(int oiid);

    @Cacheable(key = "'orderItems-uid-'+ #p0.id")
    List<OrderItem> listByUser(User user);

    @Cacheable(key = "'orderItems-oid-'+ #p0.id")
    List<OrderItem> listByOrder(Order order);

    @Cacheable(key = "'orderItems-pid-'+ #p0.id")
    List<OrderItem> listByProduct(Product product);

}
