package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.Property;
import com.xiaoxin.demo.util.Page4Navigator;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName PropertyService
 * @createDate 2019/11/15 09:14
 */
public interface PropertyService {
    /**
     * 新增属性
     *
     * @param property
     */
    public void addProperty(Property property);

    /**
     * 删除属性
     *
     * @param id
     */
    public void deletePropertyById(int id);

    /**
     * 修改属性
     *
     * @param property
     */
    public void editProperty(Property property);

    /**
     * 根据ID查询属性
     *
     * @param id
     * @return
     */
    public Property findPropertyById(int id);

    /**
     * 基于Category查询属性分页列表
     *
     * @param cid--Category
     * @param start
     * @param size
     * @param navigatePages
     * @return
     */
    public Page4Navigator<Property> propertyList(int cid, int start, int size, int navigatePages);

    /**
     * 根据Category查询属性列表
     * @param category
     * @return
     */
    public List<Property> findByCategory(Category category);
}
