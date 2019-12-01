package com.eat.service.impl;

import com.eat.enums.Sex;
import com.eat.mapper.UsersMapper;
import com.eat.pojo.Users;
import com.eat.pojo.bo.UserBo;
import com.eat.service.UserService;
import com.eat.utils.DateUtil;
import com.eat.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author weifei.song
 * @date 2019/11/30 10:09
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Override
    public boolean queryUserExist(String userName) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", userName);
        Users users = usersMapper.selectOneByExample(example);
        return users == null ? false : true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBo userBo) {
        String userId = sid.nextShort();

        Users users = new Users();
        users.setId(userId);
        users.setUsername(userBo.getUserName());
        try {
            users.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        users.setNickname(userBo.getUserName());
        users.setFace(USER_FACE);
        // 默认生日
        users.setBirthday(DateUtil.stringToDate("1900-01-01"));
        // 默认性别为 保密
        users.setSex(Sex.secret.type);
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());

        usersMapper.insert(users);
        return users;
    }

    @Override
    public Users queryUserForLogin(String userName, String password) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", userName);
        try {
            criteria.andEqualTo("password", MD5Utils.getMD5Str(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Users users = usersMapper.selectOneByExample(example);
        return users;
    }


}
