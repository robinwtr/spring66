package com.atguigu.spring.tx.controller;


import com.atguigu.spring.tx.dao.BookDao;
import com.atguigu.spring.tx.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Indexed;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    public void buyBook(Integer bookId, Integer userId) {
        bookService.buyBook(bookId,userId);
    }
}
