package com.xiaoxin.demo.dao;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.pojo.Product;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
     * 基于Category进行产品ID排序查询
     *
     * @param category
     * @return
     */
    List<Product> findByCategoryOrderById(Category category);

    /**
     * 根据名字进行模糊查询
     *
     * @param keyword
     * @param pageable
     * @return
     */
    List<Product> findByNameLike(String keyword, Pageable pageable);

    /**
     * 基于Category进行产品查询
     *
     * @param category
     * @param pageable
     * @return
     */
    Page<Product> findByCategory(Category category, Pageable pageable);

    /**
     * 查询是否能删除分类
     *
     * @param cid
     * @return
     */
    @Query(value = "select count(1) from Product where cid = :cid", nativeQuery = true)
    int canDeleteCategory(@Param("cid") int cid);
}
