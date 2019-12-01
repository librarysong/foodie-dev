package com.eat.controller;

import com.eat.pojo.Users;
import com.eat.pojo.bo.UserBo;
import com.eat.service.UserService;
import com.eat.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author weifei.song
 * @date 2019/11/30 10:21
 */

@RestController
@RequestMapping("passport")
@Api(tags = {"登录注册"})
public class PassportController {

    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExist0")
    @ApiOperation(value = "判断用户是否存在")
    public IMOOCJSONResult usernameIsExist(@RequestParam String userName) {
        if (StringUtils.isBlank(userName)) {
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        boolean exist = userService.queryUserExist(userName);
        if (exist) {
            return IMOOCJSONResult.errorMsg("用户已存在");
        }
        return IMOOCJSONResult.ok();
    }

    @PostMapping("/regist")
    @ApiOperation(value = "用户注册")
    public IMOOCJSONResult regist(@RequestBody UserBo userBo) throws Exception {
        if (StringUtils.isBlank(userBo.getUserName())) {
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        if (StringUtils.isBlank(userBo.getPassword()) || StringUtils.isBlank(userBo.getConfirmPassword())) {
            return IMOOCJSONResult.errorMsg("密码或确认密码不能为空");
        }
        if (userBo.getPassword().length() <= 6 || userBo.getConfirmPassword().length() <= 6) {
            return IMOOCJSONResult.errorMsg("密码长度不正确");
        }
        if (!userBo.getPassword().equals(userBo.getConfirmPassword())) {
            return IMOOCJSONResult.errorMsg("两次密码不一致");
        }
        boolean exist = userService.queryUserExist(userBo.getUserName());
        if (exist){
            return  IMOOCJSONResult.errorMsg("用户已存在");
        }

        Users user = userService.createUser(userBo);
        return IMOOCJSONResult.ok(user);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public IMOOCJSONResult login(@RequestBody UserBo userBo) throws Exception {
        if (StringUtils.isBlank(userBo.getUserName())) {
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        if (StringUtils.isBlank(userBo.getPassword())) {
            return IMOOCJSONResult.errorMsg("密码不能为空");
        }

        Users user = userService.queryUserForLogin(userBo.getUserName(),userBo.getPassword());
        if (user==null){
            return IMOOCJSONResult.errorMsg("用户名或密码不正确");
        }
        return IMOOCJSONResult.ok(user);
    }

}
