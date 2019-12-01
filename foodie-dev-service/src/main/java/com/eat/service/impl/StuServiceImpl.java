package com.eat.service.impl;

import com.eat.mapper.StuMapper;
import com.eat.mapper.UsersMapper;
import com.eat.pojo.Stu;
import com.eat.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weifei.song
 * @date 2019/11/30 10:09
 */
@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Override
    public Stu getSutInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveStu() {
        Stu stu=new Stu();
        stu.setId(1);
        stuMapper.updateByPrimaryKey(stu);
    }

    @Override
    public void updateStu(int id) {

    }

    @Override
    public void deleteStu(int id) {

    }
}
