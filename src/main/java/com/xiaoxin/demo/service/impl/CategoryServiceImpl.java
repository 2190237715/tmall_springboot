package com.xiaoxin.demo.service.impl;

import com.xiaoxin.demo.dao.CategoryDao;
import com.xiaoxin.demo.pojo.Category;
import com.xiaoxin.demo.service.CategoryService;
import com.xiaoxin.demo.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName CategoryServiceImpl--分类业务实现类
 * @createDate 2019/11/5 17:22
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<Category> list() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return categoryDao.findAll(sort);
    }

    @Override
    public Page4Navigator<Category> list(int start, int size, int navigatePages) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page pageFromJPA = categoryDao.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    @Override
    public void add(Category category) {
        categoryDao.save(category);
    }

}
