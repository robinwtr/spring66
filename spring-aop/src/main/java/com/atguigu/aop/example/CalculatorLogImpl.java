package com.atguigu.aop.example;

public class CalculatorLogImpl implements Calculator{
    @Override
    public int add(int a, int b) {
        System.out.println("add 开始 a="+a+" b="+b);
        int res = a + b;
        System.out.println("add方法内部 res=" + res);
        System.out.println("add 结束 res=" + res);
        return res;
    }
    @Override
    public int subtract(int a, int b) {
        System.out.println("sub 开始 a="+a+" b="+b);
        int res = a - b;
        System.out.println("sub方法内部 res=" + res);
        System.out.println("sub 结束 res=" + res);
        return res;
    }
    @Override
    public int multiply(int a, int b) {
        System.out.println("mul 开始 a="+a+" b="+b);
        int res = a * b;
        System.out.println("mul方法内部 res=" + res);
        System.out.println("mul 结束 res=" + res);
        return res;
    }
    @Override
    public int divide(int a, int b) {
        System.out.println("div 开始 a="+a+" b="+b);
        int res = a / b;
        System.out.println("div方法内部 res=" + res);
        System.out.println("div 结束 res=" + res);
        return res;
    }
}
