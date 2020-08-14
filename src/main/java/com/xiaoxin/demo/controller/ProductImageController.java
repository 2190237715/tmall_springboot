package com.xiaoxin.demo.controller;

import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.ProductImage;
import com.xiaoxin.demo.service.ProductImageService;
import com.xiaoxin.demo.service.ProductService;
import com.xiaoxin.demo.util.ImageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ProductImageController--产品图片管理控制器
 * @createDate 2019/11/19 17:28
 */
@RestController
@Api(tags = "产品图片管理接口描述")
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;

    /**
     * 根据不同类型（单个/详细）和产品查询产品图片列表
     *
     * @param type
     * @param pid
     * @return
     */
    @GetMapping("products/{pid}/productImages")
    @ApiOperation(value ="查询产品图片列表" ,notes = "根据不同类型（单个/详细）和产品查询产品图片列表")
    public List<ProductImage> productImageList(@RequestParam("type") String type,
                                               @PathVariable("pid") int pid) {
        List<ProductImage> productImageList;
        Product product = productService.findProductById(pid);
        if (productImageService.type_single.equals(type)) {
            productImageList = productImageService.listSingleProductImages(product);
        } else if (productImageService.type_detail.equals(type)) {
            productImageList = productImageService.listDetailProductImages(product);
        } else {
            productImageList = new ArrayList<>();
        }
        return productImageList;
    }

    /**
     * 新增图片
     *
     * @param pid
     * @param type
     * @param image
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/productImages")
    @ApiOperation(value ="新增图片" ,notes = "待定")
    public ProductImage addProductImage(@RequestParam("pid") int pid, @RequestParam("type") String type, MultipartFile image, HttpServletRequest request) throws Exception {
        ProductImage productImage = new ProductImage();
        Product product = productService.findProductById(pid);
        productImage.setProduct(product);
        productImage.setType(type);
        productImageService.addProductImage(productImage);
        String folder = "img/";
        if (ProductImageService.type_single.equals(productImage.getType())) {
            folder += "productSingle";
        } else {
            folder += "productDetail";
        }
        File imageFolder = new File(request.getServletContext().getRealPath(folder));
        File file = new File(imageFolder, productImage.getId() + ".jpg");
        String fileName = file.getName();
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        try {
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ProductImageService.type_single.equals(productImage.getType())) {
            String imageFolder_small = request.getServletContext().getRealPath("img/productSingle_small");
            String imageFolder_middle = request.getServletContext().getRealPath("img/productSingle_middle");
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.getParentFile().mkdirs();
            f_middle.getParentFile().mkdirs();
            ImageUtil.resizeImage(file, 56, 56, f_small);
            ImageUtil.resizeImage(file, 217, 190, f_middle);
        }

        return productImage;
    }

    /**
     * 删除图片
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @DeleteMapping("/productImages/{id}")
    @ApiOperation(value ="删除图片" ,notes = "待定")
    public String deleteProductImageById(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        ProductImage bean = productImageService.findProductImageById(id);
        productImageService.deleteProductImageById(id);
        String folder = "img/";
        if (ProductImageService.type_single.equals(bean.getType()))
            folder += "productSingle";
        else
            folder += "productDetail";

        File imageFolder = new File(request.getServletContext().getRealPath(folder));
        File file = new File(imageFolder, bean.getId() + ".jpg");
        String fileName = file.getName();
        file.delete();
        if (ProductImageService.type_single.equals(bean.getType())) {
            String imageFolder_small = request.getServletContext().getRealPath("img/productSingle_small");
            String imageFolder_middle = request.getServletContext().getRealPath("img/productSingle_middle");
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.delete();
            f_middle.delete();
        }
        return null;
    }

}
