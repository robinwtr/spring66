package com.atguigu.spring.junit.junit5;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class testAllJunit4 {

    @Autowired
    private User user;

    @Test
    public void test() {
        System.out.println(user);
        user.run();
    }
}
