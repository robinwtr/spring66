package com.atguigu.mvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    /**
     * 用RESTFul模拟用户资源的增删改查
     * /user    GET    查询所有用户信息
     * /user/1  GET    根据用户id查询用户信息
     * /user    POST   添加用户信息
     * /user/1  DELETE 根据用户id删除用户信息
     * /user    PUT    修改用户信息
     * */


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAllUser() {
        System.out.println("查询所有用户信息");
        return "success";
    }
}
