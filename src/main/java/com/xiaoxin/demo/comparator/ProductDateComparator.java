package com.xiaoxin.demo.comparator;

import com.xiaoxin.demo.pojo.Product;

import java.util.Comparator;

/**
 * 根据创建日期排序
 */
public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        return product1.getCreateDate().compareTo(product2.getCreateDate());
    }
}
