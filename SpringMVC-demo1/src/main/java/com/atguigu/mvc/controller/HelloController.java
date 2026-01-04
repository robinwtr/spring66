package com.atguigu.mvc.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    // "/" --->/WEB-INF/templates/index.html
    @RequestMapping(value = "/")
    public String hello() {
        return "index";
    }
    @RequestMapping("/target")
    public String toTarget(){
        return "target";
    }
}
