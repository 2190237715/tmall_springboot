package com.xiaoxin.demo.dao;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName PropertyDao
 * @createDate 2019/11/15 09:08
 */
@Repository
public interface PropertyDao extends JpaRepository<Property, Integer> {
    /**
     * 基于Category进行分页查询
     * @param category
     * @param pageable
     * @return
     */
    Page<Property> findByCategory(Category category, Pageable pageable);
}
