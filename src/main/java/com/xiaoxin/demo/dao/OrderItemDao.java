package com.xiaoxin.demo.dao;

import com.xiaoxin.demo.pojo.Order;
import com.xiaoxin.demo.pojo.OrderItem;
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
     * 根据订单查询订单详情并降序排序
     *
     * @param order
     * @return
     */
    List<OrderItem> findByOrderOrderByIdDesc(Order order);
}
