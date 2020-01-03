package com.xiaoxin.demo.dao;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ProductDao
 * @createDate 2019/11/19 13:00
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    /**
     * 基于Category进行产品查询
     *
     * @param category
     * @param pageable
     * @return
     */
    Page<Product> findByCategory(Category category, Pageable pageable);

    /**
     * 基于Category进行产品ID排序查询
     * @param category
     * @return
     */
    List<Product> findByCategoryOrderById(Category category);
}
