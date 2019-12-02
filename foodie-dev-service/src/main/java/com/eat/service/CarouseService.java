package com.eat.service;

import com.eat.pojo.Carousel;

import java.util.List;


/**
 * @author weifei.song
 * @date 2019/12/2 22:33
 */
public interface CarouseService {

    List<Carousel> queryAll(Integer isShow);
}
