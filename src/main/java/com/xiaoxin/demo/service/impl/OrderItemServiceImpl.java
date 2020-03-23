package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.OrderItemDao;
import com.xiaoxin.demo.pojo.Order;
import com.xiaoxin.demo.pojo.OrderItem;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.User;
import com.xiaoxin.demo.service.OrderItemService;
import com.xiaoxin.demo.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName OrderItemServiceImpl
 * @createDate 2019/12/2 16:28
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    ProductImageService productImageService;

    @Override
    public void fill(List<Order> orders) {
        for (Order order : orders) {
            fill(order);
        }
    }

    @Override
    public void fill(Order order) {
        List<OrderItem> orderItems = findByOrder(order);
        float total = 0;
        int totalNumber = 0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
            totalNumber += orderItem.getNumber();
            productImageService.setFirstProductImage(orderItem.getProduct());
        }
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);
    }

    @Override
    public List<OrderItem> findByOrder(Order order) {
        return null;
    }


    @Override
    public int getSaleCount(Product product) {
        List<OrderItem> orderItems = findByProduct(product);
        int result = 0;
        for (OrderItem orderItem : orderItems
        ) {
            if (null != orderItem.getOrder()) {
                if (null != orderItem.getOrder() && null != orderItem.getOrder().getPayDate()) {
                    result += orderItem.getNumber();
                }
            }
        }
        return result;
    }

    @Override
    public List<OrderItem> findByOrderOrderByIdDesc(Order order) {
        return orderItemDao.findByOrderOrderByIdDesc(order);
    }


    @Override
    public List<OrderItem> findByProduct(Product product) {
        return orderItemDao.findByProduct(product);
    }

    @Override
    public List<OrderItem> listByUser(User user) {
        return orderItemDao.findByUserAndOrderIsNull(user);
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        orderItemDao.save(orderItem);
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        orderItemDao.save(orderItem);
    }

    @Override
    public OrderItem findOrderItemById(int oiid) {
        return orderItemDao.findById(oiid).get();
    }

    @Override
    public void deleteOrderItem(int oiid) {
        orderItemDao.deleteById(oiid);
    }


}
