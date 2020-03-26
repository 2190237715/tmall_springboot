package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.ProductImageDao;
import com.xiaoxin.demo.pojo.OrderItem;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.ProductImage;
import com.xiaoxin.demo.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ProductImageServiceImpl
 * @createDate 2019/11/19 17:01
 */
@Service
@CacheConfig(cacheNames="productImages")
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageDao productImageDao;

    public static final String type_single = "single";
    public static final String type_detail = "detail";

    @Override
    @CacheEvict(allEntries=true)
    public void addProductImage(ProductImage productImage) {
        productImageDao.save(productImage);
    }

    @Override
    @CacheEvict(allEntries=true)
    public void deleteProductImageById(int id) {
        productImageDao.deleteById(id);
    }

    @Override
    @Cacheable(key="'productImages-one-'+ #p0")
    public ProductImage findProductImageById(int id) {
        return productImageDao.findById(id).get();
    }

    @Override
    @Cacheable(key="'productImages-single-pid-'+ #p0.id")
    public List<ProductImage> listSingleProductImages(Product product) {
        return productImageDao.findByProductAndTypeOrderByIdDesc(product, type_single);
    }

    @Override
    @Cacheable(key="'productImages-detail-pid-'+ #p0.id")
    public List<ProductImage> listDetailProductImages(Product product) {
        return productImageDao.findByProductAndTypeOrderByIdDesc(product, type_detail);
    }

    @Override
    public void setFirstProductImage(Product product) {
        List<ProductImage> singleImages = listSingleProductImages(product);
        if (!singleImages.isEmpty()) {
            product.setFirstProductImage(singleImages.get(0));
        } else {
            product.setFirstProductImage(new ProductImage());//这样做是考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。
        }
    }

    @Override
    public void setFirstProductImages(List<Product> products) {
        for (Product product : products) {
            setFirstProductImage(product);
        }
    }

    @Override
    public void setFirstProductImagesOnOrderItems(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems
        ) {
            setFirstProductImage(orderItem.getProduct());
        }
    }
}
