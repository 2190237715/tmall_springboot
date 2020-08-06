package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.User;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName UserService
 * @createDate 2019/11/22 15:23
 */
@CacheConfig(cacheNames = "users")
public interface UserService {

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @CacheEvict(allEntries = true)
    User addUser(User user);

    /**
     * 根据用户名查询用户
     *
     * @param name
     * @return
     */
    @Cacheable(key = "'users-one-name-'+ #p0")
    User findUserByName(String name);

    /**
     * 是否被注册
     *
     * @param naem
     * @return
     */
    boolean isExist(String naem);

    /**
     * 根据用户名与密码判断是否可以登陆
     *
     * @param name
     * @param password
     * @return
     */
    @Cacheable(key = "'users-one-name-'+ #p0 +'-password-'+ #p1")
    User login(String name, String password);

    /**
     * 查询用户列表
     *
     * @param start
     * @param size
     * @param navigatePages
     * @return
     */
    @Cacheable(key = "'users-page-'+#p0+ '-' + #p1")
    Page4Navigator<User> UserList(int start, int size, int navigatePages);
}
