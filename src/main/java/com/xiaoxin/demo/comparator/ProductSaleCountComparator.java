package com.xiaoxin.demo.comparator;

import com.xiaoxin.demo.pojo.Product;

import java.util.Comparator;

/**
 * 根据销量排序
 */
public class ProductSaleCountComparator implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        return product2.getSaleCount() - product1.getSaleCount();
    }
}
