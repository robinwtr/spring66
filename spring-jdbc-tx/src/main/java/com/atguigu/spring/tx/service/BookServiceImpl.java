package com.atguigu.spring.tx.service;

import com.atguigu.spring.tx.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Override
    public void buyBook(Integer bookId, Integer userId) {
        //根据图书id 查看价格
        Integer price = bookDao.getBookPriceByBookId(bookId);
        //库存量-1
        bookDao.updateStock(bookId);
        //用户余额-价格
        bookDao.updateUserBalance(userId,price);
    }
}
