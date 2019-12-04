package com.eat.service.impl;

import com.eat.mapper.CategoryMapper;
import com.eat.pojo.Category;
import com.eat.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author weifei.song
 * @date 2019/12/2 22:35
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        return categoryList;
    }
}
