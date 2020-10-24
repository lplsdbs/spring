package com.cloud.eurekaserver.controller;

import com.cloud.eurekaserver.dto.UserLoginDTO;
import com.cloud.eurekaserver.entity.User;
import com.cloud.eurekaserver.service.impl.UserServiceDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 *测试的时候一定注意公密钥的对应正确
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceDetail userServiceDetail;

    @PostMapping("/register")
    public User postUser(@RequestParam("username") String username , @RequestParam("password") String password){
        //参数判断，省略
       return userServiceDetail.insertUser(username,password);
    }

    @PostMapping("/login")
    public UserLoginDTO login(@RequestParam("username") String username , @RequestParam("password") String password){
        //参数判断，省略
        return userServiceDetail.login(username,password);
    }
    //
    @GetMapping("/foo")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String getfoo(){
        return "foo";
    }
}
