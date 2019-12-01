package com.eat.service;

import com.eat.pojo.Stu;

/**
 * @author weifei.song
 * @date 2019/11/30 10:07
 */
public interface StuService {

    Stu getSutInfo(int id);

    void saveStu();

    void updateStu(int id);

    void  deleteStu(int id);
}
