package com.xiaoxin.demo.controller;

import com.xiaoxin.demo.pojo.Property;
import com.xiaoxin.demo.service.PropertyService;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName PropertyController--属性控制器
 * @createDate 2019/11/15 15:42
 */
@RestController
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
    public String deletePropertyById(@PathVariable("id") int id) {
        propertyService.deleteProperty(id);
        return null;
    }

    /**
     * 修改属性值
     *
     * @param property
     * @return
     */
    @PutMapping("/properties")
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
    public Page4Navigator<Property> addProperty(@PathVariable("cid") int cid,
                                                @RequestParam(value = "start", defaultValue = "0") int start,
                                                @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        Page4Navigator<Property> page = propertyService.propertyList(cid, start, size, 5);
        return page;

    }
}
