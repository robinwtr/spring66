package com.atguigu.spring.junit.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:bean.xml")
public class testAllJunit5 {

    @Autowired
    private User user;

    @Test
    public void test1(){
        System.out.println(user);
        user.run();
    }
}
