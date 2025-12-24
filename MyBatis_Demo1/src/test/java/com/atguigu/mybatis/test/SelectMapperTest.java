package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.SelectMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SelectMapperTest {

    /**
     * Mybatis的各种查询功能
     * 1. 查询的结果只有一条
     * a. 可以通过实体类对象接收
     * b. 可以通过list集合接收
     * c. 可以通过map集合接收 特别是联合多张表查询时 没有特定的实体类对象时 可以考虑用map集合接收 正好对应前端的json格式
     * 2. 查询出来的数据有多条
     * a. 可以通过实体类类型的list集合接收
     * b. 可以通过map类型的list集合接收
     * c. @MapKey("id")
     */

    @Test
    public void test1() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        List<User> list = selectMapper.selectUserById(2);
        System.out.println(Arrays.toString(list.toArray()));
    }

    @Test
    public void test2() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        List<User> list = selectMapper.selectAllUser();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void test3() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        Integer count = selectMapper.getCount();
        System.out.println(count);
    }

    @Test
    public void test4() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> userByIdToMap = selectMapper.getUserByIdToMap(2);
        for (Map.Entry<String, Object> entry : userByIdToMap.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    @Test
    public void test5() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> allUserToMap = selectMapper.getAllUserToMap();
        System.out.println(allUserToMap);
    }
}
