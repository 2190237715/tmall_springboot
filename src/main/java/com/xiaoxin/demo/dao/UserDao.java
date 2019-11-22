package com.xiaoxin.demo.dao;

import com.xiaoxin.demo.pojo.User;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName UserDao
 * @createDate 2019/11/22 15:21
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
}
