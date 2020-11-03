package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Order;
import com.xiaoxin.demo.pojo.OrderItem;
import com.xiaoxin.demo.pojo.User;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName OrderService
 * @createDate 2019/11/25 18:27
 */
@CacheConfig(cacheNames = "orders")
public interface OrderService {
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    @Cacheable(key = "'orders-page-'+#p0+ '-' + #p1")
    Page4Navigator<Order> orderList(int start, int size, int navigatePages);
    
    @Cacheable(key = "'orders-one-'+ #p0")
    Order findOrderById(int id);

    @CacheEvict(allEntries = true)
    void updateOrder(Order order);

    @CacheEvict(allEntries = true)
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    float addOrder(Order order, List<OrderItem> orderItems);

    List<Order> listByUserWithoutDelete(User user);

    @Cacheable(key = "'orders-uid-'+ #p0.id")
    List<Order> listByUserAndNotDeleted(User user);


    void removeOrderFromOrderItem(List<Order> orders);

    /**
     * 让订单滞空  防止循环读取
     *
     * @param order
     */
    void removeOrderFromOrderItem(Order order);

    /**
     * 计算价钱
     *
     * @param order
     */
    void cacl(Order order);
}
