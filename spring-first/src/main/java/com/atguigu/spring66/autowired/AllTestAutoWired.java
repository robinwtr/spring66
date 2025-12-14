package com.atguigu.spring66.autowired;

import com.atguigu.spring66.autowired.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AllTestAutoWired {
    public static void main(String[] args) {
        //加载配置类
        ApplicationContext  context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController userController = context.getBean(UserController.class);
        userController.add();

    }
}
