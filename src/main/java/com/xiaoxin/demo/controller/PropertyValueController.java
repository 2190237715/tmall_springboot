package com.xiaoxin.demo.controller;

import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.PropertyValue;
import com.xiaoxin.demo.service.ProductService;
import com.xiaoxin.demo.service.PropertyValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName PropertyValueController--产品属性管理控制器
 * @createDate 2019/11/20 16:02
 */
@RestController
@Api(tags = "产品属性接口描述")
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;

    /**
     * 修改产品属性
     *
     * @param propertyValue
     * @return
     */
    @PutMapping("/propertyValues")
    @ApiOperation(value = "修改产品属性", notes = "待定")
    public PropertyValue updatePropertyValue(@RequestBody PropertyValue propertyValue) {
        propertyValueService.updatePropertyValue(propertyValue);
        return propertyValue;
    }

    /**
     * 显示产品属性
     *
     * @param pid
     * @return
     */
    @GetMapping("/products/{pid}/propertyValues")
    @ApiOperation(value = "显示产品属性", notes = "待定")
    public List<PropertyValue> propertyValueList(@PathVariable(name = "pid") int pid) {
        Product product = productService.findProductById(pid);
        propertyValueService.init(product);
        List<PropertyValue> propertyValues = propertyValueService.propertyValueList(product);
        return propertyValues;
    }
}
