package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.CategoryDao;
import com.xiaoxin.demo.dao.OrderItemDao;
import com.xiaoxin.demo.dao.ProductDao;
import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.service.OrderItemService;
import com.xiaoxin.demo.service.ProductImageService;
import com.xiaoxin.demo.service.ProductService;
import com.xiaoxin.demo.service.ReviewService;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ProductServiceImpl
 * @createDate 2019/11/19 15:07
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    ProductImageService productImageService;

    @Override
    public void addProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public void deleteProductById(int id) {
        productDao.deleteById(id);
    }

    @Override
    public void editProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public Product findProductById(int id) {
        return productDao.findById(id).get();
    }

    @Override
    public Page4Navigator<Product> ProductList(int cid, int start, int size, int navigatePages) {
        Category category = categoryDao.findById(cid).get();
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Product> pageFromJPA = productDao.findByCategory(category, pageable);
        return new Page4Navigator(pageFromJPA, navigatePages);
    }

    @Override
    public List<Product> listByCategory(Category category) {
        return productDao.findByCategoryOrderById(category);
    }

    @Override
    public void fill(Category category) {
        List<Product> products = listByCategory(category);
        productImageService.setFirstProductImages(products);
        category.setProducts(products);
    }


    @Override
    public void fill(List<Category> categories) {
        for (Category category : categories) {
            fill(category);
        }
    }

    @Override
    public void fillByRow(List<Category> categories) {
        int productNumberEachRow = 8;
        for (Category category : categories) {
            List<Product> products = category.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i += productNumberEachRow) {
                int size = i + productNumberEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> productsOfEachRow = products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }

    @Override
    public void setSaleAndReviewNumber(Product product) {
        int saleCount = orderItemService.getSaleCount(product);
        product.setSaleCount(saleCount);
        int reviewCount = reviewService.getCount(product);
        product.setReviewCount(reviewCount);
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> products) {
        for (Product product : products
        ) {
            setSaleAndReviewNumber(product);
        }
    }
}
