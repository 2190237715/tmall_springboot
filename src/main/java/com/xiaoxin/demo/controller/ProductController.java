package com.xiaoxin.demo.controller;

import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.service.ProductImageService;
import com.xiaoxin.demo.service.ProductService;
import com.xiaoxin.demo.util.Page4Navigator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ProductController--产品管理控制器
 * @createDate 2019/11/19 15:18
 */
@RestController
@Api(tags = "产品管理接口描述")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;

    /**
     * 新增产品
     *
     * @param product
     * @return
     */
    @PostMapping("/products")
    @ApiOperation(value ="新增产品" ,notes = "待定")
    public Product addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return product;
    }

    /**
     * 根据ID删除产品
     *
     * @param id
     * @return
     */
    @DeleteMapping("/products/{id}")
    @ApiOperation(value ="删除产品" ,notes = "待定")
    public String deleteProductById(@PathVariable("id") int id) {
        //TODO 产品删除没做级联
        productService.deleteProductById(id);
        return null;
    }

    /**
     * 修改产品
     *
     * @param product
     * @return
     */
    @PutMapping("/products")
    @ApiOperation(value ="修改产品" ,notes = "待定")
    public Product editProduct(@RequestBody Product product) {
        productService.editProduct(product);
        return product;
    }

    /**
     * 根据ID查询产品
     *
     * @param id
     * @return
     */
    @GetMapping("/products/{id}")
    @ApiOperation(value ="查询产品" ,notes = "待定")
    public Product findCategoryById(@PathVariable("id") int id) {
        return productService.findProductById(id);
    }

    /**
     * 分页查询
     *
     * @param cid
     * @param start
     * @param size
     * @return
     */
    @GetMapping("/categories/{cid}/products")
    @ApiOperation(value ="分页查询" ,notes = "待定")
    public Page4Navigator<Product> productList(@PathVariable("cid") int cid,
                                               @RequestParam(value = "start", defaultValue = "0") int start,
                                               @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        Page4Navigator<Product> page = productService.ProductList(cid, start, size, 5);
        productImageService.setFirstProductImages(page.getContent());
        return page;
    }
}
