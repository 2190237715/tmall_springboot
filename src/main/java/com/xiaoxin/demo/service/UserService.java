package com.xiaoxin.demo.service;

import com.xiaoxin.demo.pojo.User;
import com.xiaoxin.demo.util.Page4Navigator;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName UserService
 * @createDate 2019/11/22 15:23
 */
public interface UserService {
    Page4Navigator<User> UserList(int start, int size, int navigatePages);
}
