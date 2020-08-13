package com.xiaoxin.demo.controller;

import com.xiaoxin.demo.pojo.Order;
import com.xiaoxin.demo.service.OrderItemService;
import com.xiaoxin.demo.service.OrderService;
import com.xiaoxin.demo.util.Page4Navigator;
import com.xiaoxin.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName OrderController--订单管理控制器
 * @createDate 2019/12/4 17:00
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    /**
     * 发货
     *
     * @param oid
     * @return
     */
    @PutMapping("deliveryOrder/{oid}")
    public Object deliveryOrder(@PathVariable("oid") int oid) {
        Order order = orderService.findOrderById(oid);
        order.setDeliveryDate(new Date());
        order.setStatus(orderService.waitConfirm);
        orderService.updateOrder(order);
        return Result.success();
    }

    /**
     * 订单分页查询
     *
     * @param start
     * @param size
     * @return
     */
    @GetMapping("/orders")
    public Page4Navigator<Order> orderList(@RequestParam(value = "start", defaultValue = "0") int start,
                                           @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        Page4Navigator<Order> page = orderService.orderList(start, size, 5);
        orderItemService.fill(page.getContent());
        List<Order> orders = page.getContent();
        orderService.removeOrderFromOrderItem(orders);
        return page;
    }


}
