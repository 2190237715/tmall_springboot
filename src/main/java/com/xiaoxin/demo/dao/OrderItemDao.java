package com.xiaoxin.demo.dao;

import com.xiaoxin.demo.pojo.Order;
import com.xiaoxin.demo.pojo.OrderItem;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName OrderItem
 * @createDate 2019/11/25 14:16
 */
public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {


    /**
     * 增加根据产品获取OrderItem
     *
     * @param product
     * @return
     */
    List<OrderItem> findByProduct(Product product);

    /**
     * 根据登陆的用户查询是否存在订单
     *
     * @param user
     * @return
     */
    List<OrderItem> findByUserAndOrderIsNull(User user);

    /**
     * 根据订单查询订单详情并降序排序
     *
     * @param order
     * @return
     */
    List<OrderItem> findByOrderOrderByIdDesc(Order order);

}
