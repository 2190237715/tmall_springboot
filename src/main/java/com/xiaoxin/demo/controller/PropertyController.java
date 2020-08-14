package com.xiaoxin.demo.controller;

import com.xiaoxin.demo.pojo.Property;
import com.xiaoxin.demo.service.PropertyService;
import com.xiaoxin.demo.util.Page4Navigator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName PropertyController--属性管理控制器
 * @createDate 2019/11/15 15:42
 */
@RestController
@Api(tags = "属性管理接口描述")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    /**
     * 新增属性值
     *
     * @param property
     * @return
     */
    @PostMapping("/properties")
    @ApiOperation(value ="新增属性值" ,notes = "待定")
    public Property addProperty(@RequestBody Property property) {
        propertyService.addProperty(property);
        return property;
    }

    /**
     * 根据ID删除属性值
     *
     * @param id
     * @return
     */
    @DeleteMapping("/properties/{id}")
    @ApiOperation(value ="删除属性值" ,notes = "待定")
    public String deletePropertyById(@PathVariable("id") int id) {
        propertyService.deletePropertyById(id);
        return null;
    }

    /**
     * 修改属性值
     *
     * @param property
     * @return
     */
    @PutMapping("/properties")
    @ApiOperation(value ="修改属性值" ,notes = "待定")
    public Property editProperty(@RequestBody Property property) {
        propertyService.editProperty(property);
        return property;
    }

    /**
     * 根据ID查询属性值
     *
     * @param id
     * @return
     */
    @GetMapping("/properties/{id}")
    @ApiOperation(value ="查询属性值" ,notes = "待定")
    public Property findCategoryById(@PathVariable("id") int id) {
        return propertyService.findPropertyById(id);
    }

    /**
     * 分页查询
     *
     * @param cid
     * @param start
     * @param size
     * @return
     */
    @GetMapping("/categories/{cid}/properties")
    @ApiOperation(value ="分页查询" ,notes = "待定")
    public Page4Navigator<Property> propertyList(@PathVariable("cid") int cid,
                                                 @RequestParam(value = "start", defaultValue = "0") int start,
                                                 @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        Page4Navigator<Property> page = propertyService.propertyList(cid, start, size, 5);
        return page;
    }
}
