package com.atguigu.aop.xmlaop;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAll {

    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beanxmlaop.xml");
        Calculator bean = context.getBean(Calculator.class);
        bean.add(2,4);

    }
}
