package com.eat.controller;

import com.eat.enums.YesOrNo;
import com.eat.mapper.CategoryMapper;
import com.eat.pojo.Carousel;
import com.eat.pojo.Category;
import com.eat.service.CarouseService;
import com.eat.service.CategoryService;
import com.eat.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author weifei.song
 * @date 2019/11/29 22:11
 */
@RestController
@RequestMapping("/index")
@Api(tags = "首页展示的相关接口")
public class IndexController {

    @Autowired
    private CarouseService carouseService;

    @Autowired
    private CategoryService CategoryService;

    @RequestMapping("/carousel")
    @ApiOperation(value = "获取首页轮播图")
    public IMOOCJSONResult carouseList() {
        List<Carousel> carouselList = carouseService.queryAll(YesOrNo.YES.type);
        return IMOOCJSONResult.ok(carouselList);
    }

    @RequestMapping("/cats")
    @ApiOperation(value = "获取商品分类(一级分类)")
    public IMOOCJSONResult cats() {
        List<Category> categoryList = CategoryService.queryAllRootLevelCat();
        return IMOOCJSONResult.ok(categoryList);
    }

}
