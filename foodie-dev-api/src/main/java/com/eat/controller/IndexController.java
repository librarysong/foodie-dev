package com.eat.controller;

import com.eat.enums.YesOrNo;
import com.eat.pojo.Carousel;
import com.eat.pojo.Category;
import com.eat.pojo.vo.CategoryVO;
import com.eat.pojo.vo.NewItemsVO;
import com.eat.service.CarouseService;
import com.eat.service.CategoryService;
import com.eat.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private CategoryService categoryService;

    @GetMapping(value = "/carousel")
    @ApiOperation(value = "获取首页轮播图")
    public IMOOCJSONResult carouseList() {
        List<Carousel> carouselList = carouseService.queryAll(YesOrNo.YES.type);
        return IMOOCJSONResult.ok(carouselList);
    }

    @GetMapping("/cats")
    @ApiOperation(value = "获取商品分类(一级分类)")
    public IMOOCJSONResult cats() {
        List<Category> categoryList = categoryService.queryAllRootLevelCat();
        return IMOOCJSONResult.ok(categoryList);
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public IMOOCJSONResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }

        List<CategoryVO> list = categoryService.getSubCatList(rootCatId);
        return IMOOCJSONResult.ok(list);
    }

    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据", notes = "查询每个一级分类下的最新6条商品数据", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public IMOOCJSONResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }

        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return IMOOCJSONResult.ok(list);
    }

}
