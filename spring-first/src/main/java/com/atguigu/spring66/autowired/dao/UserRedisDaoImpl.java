package com.atguigu.spring66.autowired.dao;


import org.springframework.stereotype.Repository;

@Repository
public class UserRedisDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("dao redis.........");
    }
}
