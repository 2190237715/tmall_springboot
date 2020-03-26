package com.xiaoxin.demo.controller;

import com.xiaoxin.demo.comparator.*;
import com.xiaoxin.demo.pojo.*;
import com.xiaoxin.demo.service.*;
import com.xiaoxin.demo.util.Result;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ForeRESTController--前台控制器
 * @createDate 2019/12/17 10:32
 */
@RestController
public class ForeRESTController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    OrderService orderService;

    /**
     * 填充产品清除重复类别
     *
     * @return
     */
    @GetMapping("/forehome")
    public List<Category> home() {
        List<Category> categories = categoryService.list();
        productService.fill(categories);
        productService.fillByRow(categories);
        categoryService.removeCategoryFromProduct(categories);
        return categories;
    }


    /**
     * 注册
     *
     * @param user 用户实体
     * @return
     */
    @PostMapping("/foreregister")
    public Result register(@RequestBody User user) {
        String name = user.getName();
        String password = user.getPassword();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExist(name);
        if (exist) {
            String message = "用户名已被占用，请重新输入！";
            return Result.fail(message);
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;//迭代次数
        String algorithmName = "md5";//使用技术
        String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.addUser(user);
        return Result.success();
    }

    /**
     * 登陆
     *
     * @param userParam 前台传的用户实体
     * @param session   会话，用于存取用户实体
     * @return
     */
    @PostMapping("/forelogin")
    public Result login(@RequestBody User userParam, HttpSession session) {
        String name = HtmlUtils.htmlEscape(userParam.getName());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, userParam.getPassword());
        try {
            subject.login(token);
            User user = userService.findUserByName(name);
            session.setAttribute("user", user);
            return Result.success();
        } catch (AuthenticationException e) {
            String message = "账号密码错误，请重新输入！";
            return Result.fail(message);
        }
    }

    @GetMapping("/foreproduct/{pid}")
    public Object product(@PathVariable("pid") int pid) {
        Product product = productService.findProductById(pid);
        List<ProductImage> productSingleImages = productImageService.listSingleProductImages(product);
        List<ProductImage> productDetailImages = productImageService.listDetailProductImages(product);
        product.setProductSingleImages(productSingleImages);
        product.setProductDetailImages(productDetailImages);

        List<PropertyValue> propertyValues = propertyValueService.propertyValueList(product);
        List<Review> reviews = reviewService.reviewList(product);
        productService.setSaleAndReviewNumber(product);
        productImageService.setFirstProductImage(product);

        Map<String, Object> map = new HashMap<>();
        map.put("product", product);
        map.put("pvs", propertyValues);
        map.put("reviews", reviews);
        return Result.success(map);
    }

    /**
     * 获取session里的user 判断是否为空 即判断是否登陆
     *
     * @param session
     * @return
     */
    @GetMapping("forecheckLogin")
    public Object checkLogin(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return Result.success();
        }
        return Result.fail("未登陆");
    }

    /**
     * 各种排序
     *
     * @param cid
     * @param sort
     * @return
     */
    @GetMapping("forecategory/{cid}")
    public Object category(@PathVariable("cid") int cid, String sort) {
        Category category = categoryService.findCategoryById(cid);
        productService.fill(category);
        productService.setSaleAndReviewNumber(category.getProducts());
        categoryService.removeCategoryFromProduct(category);
        if (null != sort) {
            switch (sort) {
                case "all":
                    Collections.sort(category.getProducts(), new ProductAllComparator());
                    break;
                case "date":
                    Collections.sort(category.getProducts(), new ProductDateComparator());
                    break;
                case "price":
                    Collections.sort(category.getProducts(), new ProductPriceComparator());
                    break;
                case "reciew":
                    Collections.sort(category.getProducts(), new ProductReviewComparator());
                    break;
                case "saleCount":
                    Collections.sort(category.getProducts(), new ProductSaleCountComparator());
                    break;
            }
        }
        return category;
    }


    /**
     * 全局搜索
     *
     * @param keyword
     * @return
     */
    @PostMapping("foresearch")
    public Object search(String keyword) {
        if (null == keyword) {
            keyword = "";
        }
        List<Product> products = productService.search(keyword, 0, 20);
        productImageService.setFirstProductImages(products);
        productService.setSaleAndReviewNumber(products);
        return products;
    }

    /**
     * 新增订单项OrderItem
     *
     * @param pid
     * @param num     页面数值
     * @param session
     * @return
     */
    private int byOneAndAddCart(int pid, int num, HttpSession session) {
        int orderItemId = 0;
        boolean found = false;
        Product product = productService.findProductById(pid);
        User user = (User) session.getAttribute("user");
        List<OrderItem> orderItems = orderItemService.listByUser(user);
        for (OrderItem orderItem : orderItems
        ) {
            if (orderItem.getProduct().getId() == pid) {
                orderItem.setNumber(orderItem.getNumber() + num);
                orderItemService.updateOrderItem(orderItem);
                found = true;
                orderItemId = orderItem.getId();
                break;
            }
        }
        if (!found) {
            OrderItem orderItem = new OrderItem();
            orderItem.setNumber(num);
            orderItem.setProduct(product);
            orderItem.setUser(user);
            orderItemService.addOrderItem(orderItem);
            orderItemId = orderItem.getId();
        }
        return orderItemId;
    }

    /**
     * 立即购买
     *
     * @param pid
     * @param num
     * @param session
     * @return
     */
    @GetMapping("forebuyone")
    public Object buyOne(int pid, int num, HttpSession session) {
        return byOneAndAddCart(pid, num, session);
    }

    /**
     * 下单结算
     *
     * @param session
     * @return
     */
    @GetMapping("forebuy")
    public Object buy(HttpSession session, String[] oiid) {
        List<OrderItem> orderItems = new ArrayList<>();
        float total = 0;
        for (String strid : oiid) {
            int id = Integer.parseInt(strid);
            OrderItem orderItem = orderItemService.findOrderItemById(id);
            total += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
            orderItems.add(orderItem);
        }
        productImageService.setFirstProductImagesOnOrderItems(orderItems);
        session.setAttribute("ois", orderItems);
        Map<String, Object> map = new HashMap<>();
        map.put("orderItems", orderItems);
        map.put("total", total);
        return Result.success(map);
    }

    /**
     * 加入购物车
     *
     * @param pid
     * @param num
     * @param session
     * @return
     */
    @GetMapping("foreaddCart")
    public Object addCart(int pid, int num, HttpSession session) {
        byOneAndAddCart(pid, num, session);
        return Result.success();
    }

    /**
     * 选中购物车
     *
     * @param session
     * @return
     */
    @GetMapping("forecart")
    public Object cart(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<OrderItem> orderItems = orderItemService.listByUser(user);
        productImageService.setFirstProductImagesOnOrderItems(orderItems);
        return orderItems;
    }

    /**
     * 修改购物车
     *
     * @param session
     * @param pid
     * @param num
     * @return
     */
    @GetMapping("forechangeOrderItem")
    public Object changeOrderItem(HttpSession session, int pid, int num) {
        User user = (User) session.getAttribute("user");
        if (null == user) {
            return Result.fail("用户未登陆");
        }
        List<OrderItem> orderItems = orderItemService.listByUser(user);
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProduct().getId() == pid) {
                orderItem.setNumber(num);
                orderItemService.updateOrderItem(orderItem);
                break;
            }
        }
        return Result.success();
    }

    /**
     * 删除订单列表
     *
     * @param session
     * @param oiid
     * @return
     */
    @GetMapping("foredeleteOrderItem")
    public Object deleteOrderItem(HttpSession session, int oiid) {
        User user = (User) session.getAttribute("user");
        if (null == user) {
            return Result.fail("用户未登陆");
        }
        orderItemService.deleteOrderItem(oiid);
        return Result.success();
    }

    /**
     * 新增订单
     *
     * @param order
     * @param session
     * @return
     */
    @PostMapping("forecreateOrder")
    public Object createOrder(Order order, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (null == user) {
            return Result.fail("用户未登陆");
        }
        //生成订单号
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
        order.setOrderCode(orderCode);
        order.setCreateDate(new Date());
        order.setUser(user);
        order.setStatus(orderService.waitPay);
        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("ois");
        float total = orderService.addOrder(order, orderItems);
        Map<String, Object> map = new HashMap<>();
        map.put("oid", order.getId());
        map.put("total", total);
        return Result.success(map);
    }

    /**
     * 扫码支付修改订单状态
     *
     * @param oid
     * @return
     */
    @GetMapping("forepayed")
    public Object payed(int oid) {
        Order order = orderService.findOrderById(oid);
        order.setPayDate(new Date());
        order.setStatus(OrderService.waitDelivery);
        orderService.updateOrder(order);
        return order;
    }

    /**
     * 查询订单
     *
     * @param session
     * @return
     */
    @GetMapping("forebought")
    public Object bought(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (null == user) {
            return Result.fail("用户未登陆");
        }
        List<Order> orders = orderService.listByUserWithoutDelete(user);
        orderService.removeOrderFromOrderItem(orders);
        return orders;
    }

    /**
     * 确认收货
     *
     * @param oid
     * @return
     */
    @GetMapping("foreconfirmPay")
    public Object confirmPay(int oid) {
        Order order = orderService.findOrderById(oid);
        orderItemService.fill(order);
        orderService.cacl(order);
        orderService.removeOrderFromOrderItem(order);
        return order;
    }

    /**
     * 确定收货成功,并修改状态
     *
     * @param oid
     * @return
     */
    @GetMapping("foreorderConfirmed")
    public Object orderConfirmed(int oid) {
        Order order = orderService.findOrderById(oid);
        order.setStatus(orderService.waitReview);
        order.setConfirmDate(new Date());
        orderService.updateOrder(order);
        return Result.success();
    }

    /**
     * 删除订单
     *
     * @param oid
     * @return
     */
    @PutMapping("foredeleteOrder")
    public Object deleteOrder(int oid) {
        Order order = orderService.findOrderById(oid);
        order.setStatus(OrderService.delete);
        orderService.updateOrder(order);
        return Result.success();
    }

    /**
     * 产品评价
     *
     * @param oid
     * @return
     */
    @GetMapping("forereview")
    public Object review(int oid) {
        Order order = orderService.findOrderById(oid);
        orderItemService.fill(order);
        orderService.removeOrderFromOrderItem(order);
        Product product = order.getOrderItems().get(0).getProduct();
        List<Review> reviews = reviewService.reviewList(product);
        productService.setSaleAndReviewNumber(product);
        Map<String, Object> map = new HashMap<>();
        map.put("o", order);
        map.put("p", product);
        map.put("reviews", reviews);
        return Result.success(map);
    }

    /**
     * 提交评价
     *
     * @param session
     * @param oid
     * @param pid
     * @param content
     * @return
     */
    @PostMapping("foredoreview")
    public Object doreview(HttpSession session, int oid, int pid, String content) {
        Order order = orderService.findOrderById(oid);
        order.setStatus(OrderService.finish);
        orderService.updateOrder(order);
        Product product = productService.findProductById(pid);
        content = HtmlUtils.htmlEscape(content);
        User user = (User) session.getAttribute("user");
        Review review = new Review();
        review.setContent(content);
        review.setCreateDate(new Date());
        review.setProduct(product);
        review.setUser(user);
        reviewService.addReview(review);
        return Result.success();
    }
}
