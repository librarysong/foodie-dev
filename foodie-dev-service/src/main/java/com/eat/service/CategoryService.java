package com.eat.service;

import com.eat.pojo.Category;

import java.util.List;


/**
 * @author weifei.song
 * @date 2019/12/2 22:33
 */
public interface CategoryService {

    List<Category> queryAllRootLevelCat();
}
