package com.atguigu;

import com.atguigu.anno.Bean;
import com.atguigu.bean.AnnotationApplicationContext;
import com.atguigu.bean.ApplicationContext;
import com.atguigu.service.UserService;
import org.junit.jupiter.api.Test;

public class TestAll {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationApplicationContext("com.atguigu");

        UserService userService = (UserService)context.getBean(UserService.class);
        System.out.println(userService);
        userService.add();
    }
}
