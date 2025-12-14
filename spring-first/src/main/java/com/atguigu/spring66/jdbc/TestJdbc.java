package com.atguigu.spring66.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJdbc {

    @Test
    public void test1() {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/spring?serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("atguigu");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }
    @Test
    public void test2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc.xml");
        DruidDataSource ds = ctx.getBean("druidDataSource", DruidDataSource.class);
        System.out.println(ds.getUrl());
    }
}
