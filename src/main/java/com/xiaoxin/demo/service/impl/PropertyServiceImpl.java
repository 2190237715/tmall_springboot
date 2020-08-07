package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.CategoryDao;
import com.xiaoxin.demo.dao.PropertyDao;
import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.Property;
import com.xiaoxin.demo.service.PropertyService;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName PropertyServiceImpl
 * @createDate 2019/11/15 09:25
 */
@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyDao propertyDao;
    @Autowired
    CategoryDao categoryDao;

    @Override
    public void addProperty(Property property) {
        propertyDao.save(property);
    }

    @Override
    public void deletePropertyById(int id) {
        propertyDao.deleteById(id);
    }

    @Override
    public void editProperty(Property property) {
        propertyDao.save(property);
    }

    @Override
    public Property findPropertyById(int id) {
        return propertyDao.findById(id).get();
    }

    @Override
    public Page4Navigator<Property> propertyList(int cid, int start, int size, int navigatePages) {
        Category category = categoryDao.findById(cid).get();
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Property> pageFromJPA = propertyDao.findByCategory(category, pageable);
        return new Page4Navigator(pageFromJPA, navigatePages);
    }
}
