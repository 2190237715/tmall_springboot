package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.UserDao;
import com.xiaoxin.demo.pojo.User;
import com.xiaoxin.demo.service.UserService;
import com.xiaoxin.demo.util.Page4Navigator;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName UserServiceImpl
 * @createDate 2019/11/22 15:25
 */
@Service
@CacheConfig(cacheNames="users")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    @CacheEvict(allEntries=true)
    public User addUser(User user) {
        return userDao.save(user);
    }

    @Override
    @Cacheable(key="'users-one-name-'+ #p0")
    public User findUserByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public boolean isExist(String name) {
        User user = findUserByName(name);
        return null != user;
    }

    @Override
    @Cacheable(key="'users-one-name-'+ #p0 +'-password-'+ #p1")
    public User login(String name, String password) {
        return userDao.getByNameAndPassword(name, password);
    }

    @Override
    @Cacheable(key="'users-page-'+#p0+ '-' + #p1")
    public Page4Navigator<User> UserList(int start, int size, int navigatePages) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<User> pageFromJPA = userDao.findAll(pageable);
        return new Page4Navigator(pageFromJPA, navigatePages);
    }
}
