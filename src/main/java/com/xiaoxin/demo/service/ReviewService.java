package com.xiaoxin.demo.service;


import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.Review;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ReviewService
 * @createDate 2020/03/13 15:31
 */
@CacheConfig(cacheNames = "reviews")
public interface ReviewService {

    /**
     * 新增评论
     *
     * @param review
     */
    @CacheEvict(allEntries = true)
    void addReview(Review review);


    /**
     * 通过产品获取评价
     *
     * @param product
     * @return
     */
    @Cacheable(key = "'reviews-count-pid-'+ #p0.id")
    int getCount(Product product);

    /**
     * 展示评论列表
     *
     * @param product
     * @return
     */
    @Cacheable(key = "'reviews-pid-'+ #p0.id")
    List<Review> reviewList(Product product);

}
