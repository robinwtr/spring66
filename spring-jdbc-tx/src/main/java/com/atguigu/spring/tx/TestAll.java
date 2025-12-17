package com.atguigu.spring.tx;


import com.atguigu.spring.tx.controller.BookController;
import com.atguigu.spring.tx.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:beans.xml")
public class TestAll {


    @Autowired
    private BookController bookController;

    @Test
    public void test() {
        bookController.buyBook(2,1);
    }
}
