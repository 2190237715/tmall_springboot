package com.xiaoxin.demo;

import com.xiaoxin.demo.dao.CategoryDao;
import com.xiaoxin.demo.pojo.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootTest
class ApplicationTests {
    @Autowired
    CategoryDao categoryDao;

    @Test
    void contextLoads() {
    }

    @Test
    void testPage() { int pageNo=2;//第二页，注意在springdatajpa中分页是从第0页开始
        int size=5;//每页5条
        //PageRequest类是Pageable接口的实现类
        PageRequest pageRequest =  PageRequest.of(pageNo-1, size);
        Page<Category> page = categoryDao.findAll(pageRequest);
        System.err.println("-----------------总记录数:"+page.getTotalElements());
        System.err.println("------------------总页数:"+page.getTotalPages());
        System.err.println("------------------当前第几页："+page.getNumber());
        System.err.println("------------------当前页面的List数据集:"+page.getContent());
        System.out.println("------------------当前页的记录数："+page.getNumberOfElements());
    }

}
