package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.OrderItemDao;
import com.xiaoxin.demo.pojo.Order;
import com.xiaoxin.demo.pojo.OrderItem;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.User;
import com.xiaoxin.demo.service.OrderItemService;
import com.xiaoxin.demo.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName OrderItemServiceImpl
 * @createDate 2019/12/2 16:28
 */
@Service
@CacheConfig(cacheNames = "orderItems")
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
        List<OrderItem> orderItems = listByOrder(order);
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
        order.setOrderItems(orderItems);
    }


    @Override
    public int getSaleCount(Product product) {
        List<OrderItem> orderItems = listByProduct(product);
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
    @Cacheable(key = "'orderItems-pid-'+ #p0.id")
    public List<OrderItem> listByProduct(Product product) {
        return orderItemDao.findByProduct(product);
    }

    @Override
    @Cacheable(key = "'orderItems-uid-'+ #p0.id")
    public List<OrderItem> listByUser(User user) {
        return orderItemDao.findByUserAndOrderIsNull(user);
    }

    @Override
    @Cacheable(key = "'orderItems-oid-'+ #p0.id")
    public List<OrderItem> listByOrder(Order order) {
        return orderItemDao.findByOrderOrderByIdDesc(order);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void updateOrderItem(OrderItem orderItem) {
        orderItemDao.save(orderItem);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void addOrderItem(OrderItem orderItem) {
        orderItemDao.save(orderItem);
    }

    @Override
    @Cacheable(key = "'orderItems-one-'+ #p0")
    public OrderItem findOrderItemById(int oiid) {
        return orderItemDao.findById(oiid).get();
    }

    @Override
    @CacheEvict(allEntries = true)
    public void deleteOrderItem(int oiid) {
        orderItemDao.deleteById(oiid);
    }


}
