package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper1.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;


public class MyBatisTest {

    @Test
    public void test() throws IOException {
        /*
        * 加载核心配置类
        *  SqlSession:代表java程序和数据库之间的会话
        *  SqlSessionFactory:是生产“SqlSession”的工厂
        *  工厂模式：如果创创建一个对象，使用的全过程基本固定，那么我们就可以把创建这个对象的相关代码封装到一个
        *  工厂类中，以后都使用这个工厂类来生产我们所需要的对象。
        * */
        InputStream is = Resources.getResourceAsStream("mybatis_config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(is);
        SqlSession session = build.openSession();
        //在getMapper底层会帮助我们创建一个UserMapper接口的实现类 并返回一个实例化的对象 （代理模式）
        UserMapper mapper = session.getMapper(UserMapper.class);
        int res = mapper.insertUeser();
        session.commit();
        System.out.println("res:"+res);
    }
}
