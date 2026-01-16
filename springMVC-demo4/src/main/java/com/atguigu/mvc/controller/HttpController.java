package com.atguigu.mvc.controller;


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

}
