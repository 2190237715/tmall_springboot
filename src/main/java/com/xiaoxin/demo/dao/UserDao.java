package com.xiaoxin.demo.dao;

import com.xiaoxin.demo.pojo.User;
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
    /**
     * 根据用户名判断是否注册
     *
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 根据用户名与密码查询用户
     *
     * @param name
     * @param password
     * @return
     */
    User getByNameAndPassword(String name, String password);
}
