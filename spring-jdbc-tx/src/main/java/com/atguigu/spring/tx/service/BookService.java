package com.atguigu.spring.tx.service;


import org.springframework.stereotype.Service;

@Service
public interface BookService {
    void buyBook(Integer bookId, Integer userId);
}
