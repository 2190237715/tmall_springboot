package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.PropertyDao;
import com.xiaoxin.demo.dao.PropertyvalueDao;
import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.Property;
import com.xiaoxin.demo.pojo.PropertyValue;
import com.xiaoxin.demo.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName PropertyValueServiceImpl
 * @createDate 2019/11/20 15:50
 */
@Service
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    PropertyvalueDao propertyvalueDao;
    @Autowired
    PropertyDao propertyDao;

    @Override
    public void init(Product product) {
        List<Property> properties = propertyDao.findByCategory(product.getCategory());
        for (Property property : properties) {
            PropertyValue propertyValue = propertyvalueDao.getByProductAndProperty(product, property);
            if (null == propertyValue) {
                propertyValue = new PropertyValue();
                propertyValue.setProduct(product);
                propertyValue.setProperty(property);
                propertyvalueDao.save(propertyValue);
            }
        }
    }

    @Override
    @Cacheable(key = "'propertyValues-one-pid-'+#p0.id+ '-ptid-' + #p1.id")
    public PropertyValue getByProductAndProperty(Product product, Property property) {
        return propertyvalueDao.getByProductAndProperty(product, property);
    }

    @Override
    public void updatePropertyValue(PropertyValue propertyValue) {
        propertyvalueDao.save(propertyValue);
    }

    @Override
    public List<PropertyValue> propertyValueList(Product product) {
        return propertyvalueDao.findByProductOrderByIdDesc(product);
    }
}
