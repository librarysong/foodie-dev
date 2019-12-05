package com.eat.service;

import com.eat.pojo.Category;
import com.eat.pojo.vo.CategoryVO;
import com.eat.pojo.vo.NewItemsVO;

import java.util.List;


/**
 * @author weifei.song
 * @date 2019/12/2 22:33
 */
public interface CategoryService {

    List<Category> queryAllRootLevelCat();


    /**
     * 根据一级分类id查询子分类信息
     *
     * @param rootCatId
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     *
     * @param rootCatId
     * @return
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
