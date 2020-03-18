package com.xiaoxin.demo.comparator;

import com.xiaoxin.demo.pojo.Product;

import java.util.Comparator;

/**
 * 根据评论数量
 */
public class ProductReviewComparator implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        return product2.getReviewCount() - product1.getReviewCount();
    }
}
