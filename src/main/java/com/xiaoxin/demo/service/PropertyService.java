package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.Property;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName PropertyService
 * @createDate 2019/11/15 09:14
 */
@CacheConfig(cacheNames = "properties")
public interface PropertyService {
    /**
     * 新增属性
     *
     * @param property
     */
    @CacheEvict(allEntries = true)
    void addProperty(Property property);

    /**
     * 删除属性
     *
     * @param id
     */
    @CacheEvict(allEntries = true)
    void deletePropertyById(int id);

    /**
     * 修改属性
     *
     * @param property
     */
    @CacheEvict(allEntries = true)
    void editProperty(Property property);

    /**
     * 根据ID查询属性
     *
     * @param id
     * @return
     */
    @Cacheable(key = "'properties-one-'+ #p0")
    Property findPropertyById(int id);

    /**
     * 基于Category查询属性分页列表
     *
     * @param cid--Category
     * @param start
     * @param size
     * @param navigatePages
     * @return
     */
    @Cacheable(key = "'properties-cid-'+#p0+'-page-'+#p1 + '-' + #p2 ")
    Page4Navigator<Property> propertyList(int cid, int start, int size, int navigatePages);

}
