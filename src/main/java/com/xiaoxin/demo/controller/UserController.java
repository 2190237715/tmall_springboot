package com.xiaoxin.demo.controller;

import com.xiaoxin.demo.pojo.User;
import com.xiaoxin.demo.service.UserService;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName UserController--用户管理控制器
 * @createDate 2019/11/22 15:37
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 分页查询用户列表
     *
     * @param start
     * @param size
     * @return
     */
    @GetMapping("/users")
    public Page4Navigator<User> UserList(@RequestParam(name = "start", defaultValue = "0") int start,
                                         @RequestParam(name = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        Page4Navigator<User> page = userService.UserList(start, size, 5);
        return page;
    }
}
