package com.atguigu.spring66.autowired.controller;


import com.atguigu.spring66.autowired.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    //根据名称注入
    @Resource
    private UserService userService;
    public UserController( UserService userService) {
        this.userService = userService;
    }

    public void add(){
        System.out.println("controller.......");
        userService.add();
    }
}
