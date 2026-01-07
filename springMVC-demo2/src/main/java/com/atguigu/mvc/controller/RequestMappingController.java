package com.atguigu.mvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/hello")
public class RequestMappingController {

    @RequestMapping(
            value = {"/testRequestMapping", "/test"},
            method = {RequestMethod.GET, RequestMethod.POST},
            params = {"username"}
    )
    public String success() {
        return "success";
    }
    @GetMapping("/testGetMapping")
    public String testGetMapping() {
        return "success";
    }
    //from表单不可以更改请求方式 请求方式只可能是get和post
    @RequestMapping(value = "/testPut",method=RequestMethod.PUT)
    public String testPut(){
        return "success";
    }

    @RequestMapping(
            value = "/testParamsAndHeaders",
            params = {"username"}
    )
    public String testParamsAndHeaders() {
        return "success";
    }

}
