package com.atguigu.spring.tx.dao;


import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;

@Repository
public interface BookDao {

    Integer getBookPriceByBookId(Integer bookId);

    void updateStock(Integer bookId);

    void updateUserBalance(Integer userId, Integer price);
}
