package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {

    //查询所有的员工信息
    List<Emp> selectAllEmp();
    //查询员工以及所对应的部门信息
    Emp selectEmpAndDeptById(@Param("eid") Integer eid);
}
