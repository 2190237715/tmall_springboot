package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.OrderDao;
import com.xiaoxin.demo.pojo.Order;
import com.xiaoxin.demo.pojo.OrderItem;
import com.xiaoxin.demo.pojo.User;
import com.xiaoxin.demo.service.OrderItemService;
import com.xiaoxin.demo.service.OrderService;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName OrderServiceImpl
 * @createDate 2019/12/4 15:09
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public Page4Navigator<Order> orderList(int start, int size, int navigatePages) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page pageFromJPA = orderDao.findAll(pageable);
        return new Page4Navigator(pageFromJPA, navigatePages);
    }

    @Override
    public void removeOrderFromOrderItem(List<Order> orders) {
        for (Order order : orders) {
            removeOrderFromOrderItem(order);
        }
    }

    @Override
    public void removeOrderFromOrderItem(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(null);
        }
    }

    @Override
    public Order findOrderById(int id) {
        return orderDao.findById(id).get();
    }

    @Override
    public void updateOrder(Order order) {
        orderDao.save(order);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public float addOrder(Order order, List<OrderItem> orderItems) {
        float total = 0;
        orderDao.save(order);
        //故意抛出异常代码用来模拟当增加订单后出现异常  需改为true
        if (false) {
            throw new RuntimeException();
        }
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
            orderItemService.updateOrderItem(orderItem);
            total += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
        }
        return total;
    }

    @Override
    public List<Order> listByUserWithoutDelete(User user) {
        List<Order> orders = listByUserAndNotDeleted(user);
        orderItemService.fill(orders);
        return orders;
    }

    @Override
    public List<Order> listByUserAndNotDeleted(User user) {
        return orderDao.findByUserAndStatusNotOrderByIdDesc(user, delete);
    }

    @Override
    public void cacl(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        float total = 0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
        }
        order.setTotal(total);
    }
}
