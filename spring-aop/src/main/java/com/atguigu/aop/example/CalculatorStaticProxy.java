package com.atguigu.aop.example;

public class CalculatorStaticProxy implements Calculator {

    //被代理的目标对象传递进来
    private Calculator calculator;
    public CalculatorStaticProxy(Calculator calculator) {
        this.calculator = calculator;
    }



    @Override
    public int add(int a, int b) {
        System.out.println("add 开始 a="+a+" b="+b);

        int res = calculator.add(a, b);

        System.out.println("add 结束 res=" + res);
        return 0;
    }

    @Override
    public int subtract(int a, int b) {
        return 0;
    }

    @Override
    public int multiply(int a, int b) {
        return 0;
    }

    @Override
    public int divide(int a, int b) {
        return 0;
    }
}
