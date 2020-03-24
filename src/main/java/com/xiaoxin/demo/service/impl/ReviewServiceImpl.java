package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.ReviewDAO;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.Review;
import com.xiaoxin.demo.service.ProductService;
import com.xiaoxin.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ReviewServiceImpl
 * @createDate 2020/03/13 15:31
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewDAO reviewDAO;
    @Autowired
    ProductService productService;

    @Override
    public void addReview(Review review) {
        reviewDAO.save(review);
    }

    @Override
    public List<Review> reviewList(Product product) {
        List<Review> reviews = reviewDAO.findByProductOrderByIdDesc(product);
        return reviews;
    }

    @Override
    public int getCount(Product product) {
        return reviewDAO.countByProduct(product);
    }
}
