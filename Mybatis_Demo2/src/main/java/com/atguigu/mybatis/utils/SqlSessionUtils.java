package com.atguigu.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtils {

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() {
        SqlSession session = null;
        try {
            InputStream is = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
            session = sessionFactory.openSession(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return session;
    }
}
