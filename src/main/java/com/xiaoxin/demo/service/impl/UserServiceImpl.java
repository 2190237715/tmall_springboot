package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.UserDao;
import com.xiaoxin.demo.pojo.User;
import com.xiaoxin.demo.service.UserService;
import com.xiaoxin.demo.util.Page4Navigator;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User addUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public boolean isExist(String name) {
        User user = findUserByName(name);
        return null != user;
    }

    @Override
    public Page4Navigator<User> UserList(int start, int size, int navigatePages) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<User> pageFromJPA = userDao.findAll(pageable);
        return new Page4Navigator(pageFromJPA, navigatePages);
    }
}
