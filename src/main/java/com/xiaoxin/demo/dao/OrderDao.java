package com.xiaoxin.demo.dao;

import com.xiaoxin.demo.pojo.Order;
import com.xiaoxin.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName OrderDao
 * @createDate 2019/11/25 18:32
 */
public interface OrderDao extends JpaRepository<Order, Integer> {

    List<Order> findByUserAndStatusNotOrderByIdDesc(User user, String status);
}
