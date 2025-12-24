package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.SqlMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

public class SqlMapperTest {

    @Test
    public void test() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SqlMapper mapper = sqlSession.getMapper(SqlMapper.class);
        List<User> userByLike = mapper.getUserByLike("a");
        System.out.println(userByLike);
    }

    @Test
    public void test2() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SqlMapper mapper = sqlSession.getMapper(SqlMapper.class);
        int i = mapper.deleteMoreUser("5,6,7");
        System.out.println(i);
    }

    @Test
    public void test3() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SqlMapper mapper = sqlSession.getMapper(SqlMapper.class);
        List<User> tUser = mapper.getUserByTableName("t_user");
        System.out.println(tUser);
    }

    @Test
    public void test4() throws Exception {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SqlMapper mapper = sqlSession.getMapper(SqlMapper.class);
        User user = new User(null, "mahaonan", "123456", 23, "男", "123098@163.com");
        mapper.insertUser(user);
        System.out.println(user);
    }
}
