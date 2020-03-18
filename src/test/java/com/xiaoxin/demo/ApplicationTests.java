package com.xiaoxin.demo;

import com.xiaoxin.demo.dao.CategoryDao;
import com.xiaoxin.demo.pojo.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootTest
class ApplicationTests {
    @Autowired
    CategoryDao categoryDao;

    @Test
    void contextLoads() {
    }

    @Test
    void testPage() {
        int pageNo = 2;//第二页，注意在springdatajpa中分页是从第0页开始
        int size = 5;//每页5条
        //PageRequest类是Pageable接口的实现类
        PageRequest pageRequest = PageRequest.of(pageNo - 1, size);
        Page<Category> page = categoryDao.findAll(pageRequest);
        System.err.println("-----------------总记录数:" + page.getTotalElements());
        System.err.println("------------------总页数:" + page.getTotalPages());
        System.err.println("------------------当前第几页：" + page.getNumber());
        System.err.println("------------------当前页面的List数据集:" + page.getContent());
        System.out.println("------------------当前页的记录数：" + page.getNumberOfElements());
    }

    @Test
    void map() {
        class contract{
            private String case_name;
            private String particular_url;
            private String cover_url;
            private String category;
            private String label;
            public String getCase_name() {
                return case_name;
            }
            public void setCase_name(String case_name) {
                this.case_name = case_name;
            }
            public String getParticular_url() {
                return particular_url;
            }
            public void setParticular_url(String particular_url) {
                this.particular_url = particular_url;
            }
            public String getCover_url() {
                return cover_url;
            }
            public void setCover_url(String cover_url) {
                this.cover_url = cover_url;
            }
            public String getCategory() {
                return category;
            }
            public void setCategory(String category) {
                this.category = category;
            }
            public String getLabel() {
                return label;
            }
            public void setLabel(String label) {
                this.label = label;
            }

            @Override
            public String toString() {
                return "contract{" +
                        "case_name='" + case_name + '\'' +
                        ", particular_url='" + particular_url + '\'' +
                        ", cover_url='" + cover_url + '\'' +
                        ", category='" + category + '\'' +
                        ", label='" + label + '\'' +
                        '}';
            }
        }
    }

    @Test
    void yueShu() {
        int sum = 0;
        for (int zheng = 1; zheng < 1000; zheng++) {
            for (int yue = 1; yue < 1000; yue++) {
                if (zheng <= yue) {
                    break;
                }
                if (zheng % yue == 0) {
                    sum += yue;
                    if (zheng == sum) {
                        System.out.println("zhengshu====" + zheng);
                    }
                }
            }
            sum=0;
        }
    }

}
