package com.xiaoxin.demo.dao;

import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.Property;
import com.xiaoxin.demo.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName PropertyvalueDao
 * @createDate 2019/11/20 09:08
 */
@Repository
public interface PropertyvalueDao extends JpaRepository<PropertyValue, Integer> {
    /**
     * 根据产品并按照ID降序查询产品属性列表
     *
     * @param product
     * @return
     */
    List<PropertyValue> findByProductOrderByIdDesc(Product product);

    /**
     * 根据产品和属性查询产品属性
     *
     * @param product
     * @param property
     * @return
     */
    PropertyValue getByProductAndProperty(Product product, Property property);
}
