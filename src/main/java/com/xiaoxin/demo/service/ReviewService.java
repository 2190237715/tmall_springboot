package com.xiaoxin.demo.service;


import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.Review;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ReviewService
 * @createDate 2020/03/13 15:31
 */
public interface ReviewService {

    /**
     * 新增评论
     *
     * @param review
     */
    void add(Review review);

    /**
     * 展示评论列表
     *
     * @param product
     * @return
     */
    List<Review> reviewList(Product product);

    /**
     * 通过产品获取评价
     *
     * @param product
     * @return
     */
    int getCount(Product product);
}
