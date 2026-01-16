package com.atguigu.mvc.controller;


import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
