package com.eat.service;

import com.eat.pojo.Users;
import com.eat.pojo.bo.UserBo;

/**
 * @author weifei.song
 * @date 2019/11/30 10:07
 */
public interface UserService {

    /**
     * 判断用户是否存在
     * @param userName
     * @return
     */
    boolean queryUserExist(String userName);

    /**
     * 创建用户
     * @param userBo
     * @return
     * @throws Exception
     */
    Users createUser(UserBo userBo) throws Exception;

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    Users queryUserForLogin(String userName,String password);
}
