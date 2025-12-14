package com.atguigu.aop.example;

public class TestAll {
    public static void main(String[] args) {
        //动态代理 创建代理对象
        ProxyFactory  proxyFactory = new ProxyFactory(new CalculatorImpl());

        Calculator calculator = (Calculator) proxyFactory.getProxy();
        calculator.add(1, 2);
        calculator.subtract(1, 2);
        calculator.multiply(4,3);
    }
}
