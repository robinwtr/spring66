package com.atguigu.spring.jdbc;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(locations = "classpath:beans.xml")
public class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1() {
        /**
        String sql = "INSERT INTO t_emp VALUES(NULL,?,?,?)";
        Object[] args = {"林平之", 20, "女"};

        //添加
        int rows = jdbcTemplate.update(sql, args);
        */
        /*
        //修改
        String sql = "UPDATE t_emp SET name =? where id =?";
        int rows = jdbcTemplate.update(sql, "小龙女", 3);
        System.out.println(rows);
         */

        //删除
        String sql = "DELETE FROM t_emp where id = ?";
        int rows = jdbcTemplate.update(sql, 3);
        System.out.println(rows);
    }

    @Test
    public void test2() {
        String sql = "select * from t_emp where id = ?";
        //rowMapper  是一个接口 用于封装
//        Emp emores = jdbcTemplate.queryForObject(sql, (rs,rowNum) -> {
//            Emp emp = new Emp();
//            emp.setId(rs.getInt("id"));
//            emp.setName(rs.getString("name"));
//            emp.setAge(rs.getInt("age"));
//            emp.setGender(rs.getString("sex"));
//            return emp;
//        },1);
//        System.out.println(emores.toString());

        Emp emp = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Emp>(Emp.class),1);
        System.out.println(emp.toString());
    }

    @Test
    public void test3() {
        String sql = "select * from t_emp ";
        List<Emp> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        System.out.println(query);
    }

    @Test
    public void test4() {
        String sql = "select count(*) from t_emp";
        Integer i = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(i);
    }
}
