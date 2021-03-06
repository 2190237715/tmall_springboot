package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.CategoryDao;
import com.xiaoxin.demo.dao.ProductDao;
import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.search.ProductEsDao;
import com.xiaoxin.demo.service.OrderItemService;
import com.xiaoxin.demo.service.ProductImageService;
import com.xiaoxin.demo.service.ProductService;
import com.xiaoxin.demo.service.ReviewService;
import com.xiaoxin.demo.util.Page4Navigator;
import com.xiaoxin.demo.util.SpringContextUtil;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
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
    ProductEsDao productEsDao;
    @Autowired
    OrderItemService orderItemService;
    @Lazy
    @Autowired
    ReviewService reviewService;
    @Autowired
    ProductImageService productImageService;

    @Override
    public void addProduct(Product product) {
        productDao.save(product);
        productEsDao.save(product);
    }

    @Override
    public void deleteProductById(int id) {
        productDao.deleteById(id);
        productEsDao.deleteById(id);
    }

    @Override
    public void editProduct(Product product) {
        productDao.save(product);
        productEsDao.save(product);
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
    public void fill(List<Category> categories) {
        for (Category category : categories) {
            fill(category);
        }
    }

    @Override
    public void fill(Category category) {
        ProductServiceImpl productService = SpringContextUtil.getBean(ProductServiceImpl.class);
        List<Product> products = productService.listByCategory(category);
        productImageService.setFirstProductImages(products);
        category.setProducts(products);
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
        for (Product product : products) {
            setSaleAndReviewNumber(product);
        }
    }

    @Override
    public List<Product> search(String keyword, int start, int size) {
        initDatabase2ES();
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
                .withQuery(QueryBuilders.queryStringQuery(keyword)).build();
        Page<Product> products = productEsDao.search(searchQuery);
        return products.getContent();
    }

    @Override
    public List<Product> searchLike(String keyword, int start, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        List<Product> products = productDao.findByNameLike("%" + keyword + "%", pageable);
        return products;
    }

    @Override
    public int canDeleteCategory(int cid) {
        return productDao.canDeleteCategory(cid);
    }


    private Pageable initDatabase2ES() {
        Pageable pageable = PageRequest.of(0, 8);
        Page<Product> page = productEsDao.findAll(pageable);
        if (page.getContent().isEmpty()) {
            Iterable<Product> products = productDao.findAll();
            for (Product product : products) {
                productEsDao.save(product);
            }
        }
        return pageable;
    }
}
