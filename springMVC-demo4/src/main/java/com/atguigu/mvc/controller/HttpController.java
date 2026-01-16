package com.atguigu.mvc.controller;


import com.atguigu.mvc.bean.User;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HttpController {


    @RequestMapping(value = "/testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println("body" + body);
        return "success";
    }

    @RequestMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> requestEntity) {
        //当前requestEntity表示整个请求报文的信息
        System.out.println("requestEntity 请求头" + requestEntity.getHeaders());
        System.out.println("requestEntity 请求体" + requestEntity.getBody());
        return "success";
    }

    @RequestMapping("/testResponse")
    public void testResponse(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.getWriter().print("hello" );
    }

    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody(){
        return "success";
    }


    @RequestMapping("/testResponseUser")
    @ResponseBody
    public User testResponseUser(){
        User user = new User(1001,"dasd","321312","nan",21,"123@qq.com");
        return user;
    }

    @RequestMapping("/testAxios")
    @ResponseBody
    public String testAxios(String username, String password){
        System.out.println(username+" "+password);
        return "hello.ajax";
    }
}
