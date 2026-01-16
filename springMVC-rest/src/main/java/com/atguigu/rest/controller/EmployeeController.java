package com.atguigu.rest.controller;

import com.atguigu.rest.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

}
