package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.Property;
import com.xiaoxin.demo.pojo.PropertyValue;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName PropertyValueService
 * @createDate 2019/11/20 09:16
 */
@CacheConfig(cacheNames = "propertyValues")
public interface PropertyValueService {

    /**
     * 初始化
     *
     * @param product
     */
    void init(Product product);

    /**
     * 修改产品属性
     *
     * @param propertyValue
     */
    @CacheEvict(allEntries = true)
    void updatePropertyValue(PropertyValue propertyValue);

    /**
     * 根据产品查询产品属性列表
     *
     * @param product
     * @return
     */
    @Cacheable(key = "'propertyValues-pid-'+ #p0.id")
    List<PropertyValue> propertyValueList(Product product);

}
