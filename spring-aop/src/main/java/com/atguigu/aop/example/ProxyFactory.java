package com.atguigu.aop.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyFactory  {

    //目标对象
    private Object target;
    public ProxyFactory(Object target) {
        this.target = target;
    }

    //返回代理对象
    public Object getProxy() {
        /**
         * newProxyInstance()方法
         * 有三个参数
         * 1.ClassLoader:加载动态生成代理类的类加载器
         * 2.Class<?>[] interface:目标对象实现的所有接口的class类型数组
         * 3.InvocationHandler:设置代理对象实现目标对象方法的过程
         */

        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler invocationHandler = new InvocationHandler() {
            //1.代理对象 2.需要重写目标对象的方法 3.method中的参数数组
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //调用目标方法
                Object result = null;
                System.out.println("[动态代理][日志]" + method.getName() + "---参数:" + Arrays.toString(args));
                result = method.invoke(target, args);
                System.out.println("[动态代理][日志]" + method.getName() + "---结果:" + result);
                return result;
            }
        };
        Object object = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        return object;
    }
}
