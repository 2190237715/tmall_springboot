package com.xiaoxin.demo;

import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTests {
    @Test
    public void sizeTest() {
        List<Object> array = new ArrayList();
        array.add("a");
        array.add("a");
        array.add("a");
        System.out.println(array.size());
    }

    @Test
    public void numTest() {
        float sum = 0;
        sum = (float) (4935.00 + 2208.70 + 1710.00 + 11038.80 + 5928.00);
        System.out.println(sum);
    }

    @Autowired
    CategoryService categoryService;

    @Test
    public void strTest() {
        categoryService.findCategoryById(82);
        List<Category> categories = categoryService.list();
        for (Category category : categories) {
            System.out.println(category.getName());
        }
//        categoryService.categoryList(1, 2, 4);
    }
}
