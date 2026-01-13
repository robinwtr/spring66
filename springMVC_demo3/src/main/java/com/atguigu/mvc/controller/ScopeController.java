package com.atguigu.mvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ScopeController {

     //使用ServletApi向request域对象共享数据
     @RequestMapping("/testRequestByServletAPI")
     public String testRequestByServletAPI(HttpServletRequest request) {
          request.setAttribute("testRequestScope","Hello,servletAPI");
          return "success";
     }
}
