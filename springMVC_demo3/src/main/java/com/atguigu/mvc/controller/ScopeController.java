package com.atguigu.mvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Controller
public class ScopeController {

     //使用ServletApi向request域对象共享数据
     @RequestMapping("/testRequestByServletAPI")
     public String testRequestByServletAPI(HttpServletRequest request) {
          request.setAttribute("testRequestScope","Hello,servletAPI");
          return "success";
     }
     @RequestMapping("/testModelAndView")
     public ModelAndView testModelAndView(HttpServletRequest request) {
          ModelAndView modelAndView = new ModelAndView();
          //处理模型数据，向request域中共享参数
          modelAndView.addObject("testRequestScope","Hello,ModelAndView");
          //设置视图名称
          modelAndView.setViewName("success");
          return modelAndView;
     }
     @RequestMapping("/testModel")
     public String testModel(Model model) {
          model.addAttribute("testRequestScope","Hello,Model");
          System.out.println(model.getClass().getName());
          return "success";
     }
     @RequestMapping("/testMap")
     public String testMap(Map<String, Object> map) {
          map.put("testRequestScope", "Hello,Map");
          System.out.println(map.getClass().getName());
          return "success";
     }
     @RequestMapping("/testModelMap")
     public String testModelMap(ModelMap modelMap) {
          modelMap.put("testRequestScope", "Hello,ModelMap");
          System.out.println(modelMap.getClass().getName());
          return "success";
     }
}
