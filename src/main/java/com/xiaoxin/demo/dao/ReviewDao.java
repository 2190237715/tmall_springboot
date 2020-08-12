package com.xiaoxin.demo.dao;

import com.xiaoxin.demo.pojo.Product;
import com.xiaoxin.demo.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ReviewDAO
 * @createDate 2020/03/13 15:31
 */
public interface ReviewDao extends JpaRepository<Review, Integer> {

    /**
     * 返回某产品对应的评价数量
     *
     * @param product
     * @return
     */
    int countByProduct(Product product);

    /**
     * 返回某产品对应的评价集合
     *
     * @param product
     * @return
     */
    List<Review> findByProductOrderByIdDesc(Product product);

}
