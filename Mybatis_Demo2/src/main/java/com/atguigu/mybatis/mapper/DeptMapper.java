package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {
    //通过分布查询 查询员工和员工的部门 分布查询第二步 通过did查询员工对应的部门
    Dept selectEmpAndDeptByStepTwo(@Param("did") Integer did);
    //部门以及部门中所有员工信息
    Dept selectDeptAndEmp(@Param("did") Integer did);

}
