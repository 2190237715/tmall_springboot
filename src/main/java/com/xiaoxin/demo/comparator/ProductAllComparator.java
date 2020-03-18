package com.xiaoxin.demo.comparator;

import com.xiaoxin.demo.pojo.Product;

import java.util.Comparator;

/**
 * 综合比较    销量*评价
 */
public class ProductAllComparator implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        return product2.getReviewCount() * product2.getSaleCount() - product1.getReviewCount() * product1.getSaleCount();
    }
}
