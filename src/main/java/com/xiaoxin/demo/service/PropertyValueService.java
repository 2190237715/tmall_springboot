package com.xiaoxin.demo.service;

import com.xiaoxin.demo.dao.PropertyvalueDao;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.Property;
import com.xiaoxin.demo.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName PropertyValueService
 * @createDate 2019/11/20 09:16
 */
public interface PropertyValueService {

    /**
     * 初始化
     *
     * @param product
     */
    void init(Product product);

    /**
     * 根据产品和属性查询产品属性
     *
     * @param product
     * @param property
     * @return
     */
    PropertyValue getByProductAndProperty(Product product, Property property);

    /**
     * 修改产品属性
     *
     * @param propertyValue
     */
    void updatePropertyValue(PropertyValue propertyValue);

    /**
     * 根据产品查询产品属性列表
     *
     * @param product
     * @return
     */
    List<PropertyValue> propertyValueList(Product product);

}
