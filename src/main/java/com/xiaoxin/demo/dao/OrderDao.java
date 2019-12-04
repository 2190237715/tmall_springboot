package com.xiaoxin.demo.dao;

import com.xiaoxin.demo.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName OrderDao
 * @createDate 2019/11/25 18:32
 */
public interface OrderDao extends JpaRepository<Order, Integer> {
}
