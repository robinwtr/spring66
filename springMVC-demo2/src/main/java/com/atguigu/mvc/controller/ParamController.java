package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class ParamController {

    @RequestMapping("/testServletAPI")
    //形参位置的request表示当前请求
    public String testServletAPI(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:"+username+","+"password:"+password);
        return "success";
    }

    @RequestMapping("/testParam")
    public String testParam(String username, String password, String[] hobby) {
        System.out.println("username:"+username+","+"password:"+password+",hobby:"+ Arrays.toString(hobby));
        return "success";
    }
}
