package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {

    //查询所有的员工信息
    List<Emp> selectAllEmp();
    //查询员工以及所对应的部门信息
    Emp selectEmpAndDeptById(@Param("eid") Integer eid);
    //通过分布查询 查询员工和员工的部门 分布查询第一步
    Emp selectEmpAndDeptByStepOne(@Param("eid") Integer eid);
    /**
     * 通过分布查询查询部门以及部门中所有的员工信息
     * 分布查询第二步：根据did查询员工信息
     */
    List<Emp> getDeptAndEmpByStepTwo(@Param("did") Integer did);
}
