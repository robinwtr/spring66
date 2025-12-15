package com.atguigu.aop.xmlaop;


import org.springframework.stereotype.Component;

@Component
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        int res = a + b;
        System.out.println("add方法内部 res=" + res);
        return res;
    }
    @Override
    public int subtract(int a, int b) {
        int res = a - b;
        System.out.println("sub方法内部 res=" + res);
        return res;
    }
    @Override
    public int multiply(int a, int b) {
        int res = a * b;
        System.out.println("mul方法内部 res=" + res);
        return res;
    }
    @Override
    public int divide(int a, int b) {
        int res = a / b;
        System.out.println("div方法内部 res=" + res);
        return res;
    }

}
