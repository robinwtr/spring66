package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.ParameterMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterMapperTest {

    /**
     * Mybatis获取参数值的两种方式：${}和#{}
     * ${}本质是字符串拼接
     * #{}本质是占位符赋值
     *
     * mybatis 获取参数值的各种情况：
     * 1. mapper接口方法的参数为单个的字面量类型
     * 可以通过${}和#{}以任意的名称获取参数值， 但是需要注意${}的单引号问题
     * 2. mapper接口方法的参数为多个时
     * 此时Mybatis会将这些参数放在一个map集合中，以两种方式进行存储
     * a. 以arg0和arg1......为键，以参数为值
     * b. 以param1，param2......为键，以参数为值
     * 因此只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题
     * 3. mapper方法参数有多个时 可以手动将参数放入map集合中
     * 4. mapper接口方法的参数是一个实体类类型的参数
     */



    @Test
    public void test() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> list = mapper.getAllUser();
        list.forEach(user -> System.out.println(user));
    }

    @Test
    public void test2() throws Exception {
        String username = "root";
        Class.forName("");
        Connection connection = DriverManager.getConnection("", "", "");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_user where username=?");
        preparedStatement.setString(1, username);
    }

    @Test
    public void test3() throws Exception {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User root = mapper.getUserByUsername("root");
        System.out.println(root);
    }

    @Test
    public void test4() throws Exception {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User root = mapper.checkLogin("root", "123456");
        if (root != null) {
            System.out.println(root);
            System.out.println("登陆成功！");
        }else {
            System.out.println("请输入正确的账号密码！");
        }
    }

    @Test
    public void test5() throws Exception {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        Map<String, Object> param = new HashMap<>();
        param.put("username", "root");
        param.put("password", "123456");
        User root = mapper.checkLoginByMap(param);
        if (root != null) {
            System.out.println(root);
            System.out.println("登陆成功！");
        }else {
            System.out.println("请输入正确的账号密码！");
        }
    }
    @Test
    public void test6() throws Exception {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        int i = mapper.insertUser(new User(null, "lisi", "123", 23, "女", "123459@163.com"));
        System.out.println(i);
    }
}
