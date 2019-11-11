package com.xiaoxin.demo.dao;

import com.xiaoxin.demo.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName CategoryDao
 * @createDate 2019/11/5 17:08
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> , PagingAndSortingRepository<Category, Integer> {

}
