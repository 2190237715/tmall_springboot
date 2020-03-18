package com.xiaoxin.demo.comparator;

import com.xiaoxin.demo.pojo.Product;

import java.util.Comparator;

/**
 * 根据价格高低排序
 */
public class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        return (int) (product1.getPromotePrice() - product2.getPromotePrice());
    }
}
