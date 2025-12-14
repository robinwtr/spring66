package com.atguigu.reflect;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestCar {

    //获取Class对象
    @Test
    public void test1() throws Exception {
        //类名.class
        Class clazz1 = Car.class;
        //对象.getClass()
        Class clazz2 = new Car().getClass();
        //Class.forName("全路径")
        Class clazz3 = Class.forName("com.atguigu.reflect.Car");
        //实例化
        Car car = (Car) clazz3.getDeclaredConstructor().newInstance();
        System.out.println(car);
    }
    //获取构造方法
    @Test
    public void test2() throws Exception {
        Class clazz1 = Car.class;
        Constructor[] constructors = clazz1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName()+"::"+constructor.getParameterTypes().length);
        }
        //指定有参构造
        //public构造函数
        Constructor constructors1 = clazz1.getConstructor(String.class, int.class, String.class);
        Car car = (Car)constructors1.newInstance("A6L", 10, "red");
        System.out.println(car.toString());

        //private构造函数
        Constructor declaredConstructor = clazz1.getDeclaredConstructor(String.class, int.class, String.class);
        declaredConstructor.setAccessible(true);
        Car car1 = (Car)constructors1.newInstance("E300l", 5, "white");
        System.out.println(car1.toString());
    }

    //获取属性
    @Test
    public void test3() throws Exception {
        Class clazz = Car.class;
        Car car = (Car) clazz.getDeclaredConstructor().newInstance();
        //获取所有的public属性
        Field[] fields = clazz.getFields();
        //获取所有的属性
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals("name")){
                //允许访问
                field.setAccessible(true);
                field.set(car,"Maybach");
            }
            System.out.println(field.getName());
            System.out.println(car.toString());
        }
    }
    //获取方法
    @Test
    public void test4() throws Exception {
        Car car = new Car("A8", 1, "pray");
        Class clazz = car.getClass();
        //public方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("toString")){
                method.setAccessible(true);
                String invoke = (String) method.invoke(car);
                System.out.println("toString方法执行了" + invoke);
            }
        }
        //所有方法(private)
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().equals("run")){
                method.setAccessible(true);
                method.invoke(car);
            }
        }
    }
}
