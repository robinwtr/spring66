package com.atguigu.spring66.bean;

public class Orders {
    private String oname;


    public Orders(){
        System.out.println("第一步 执行无参构造创建bean实例");
    }

    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("第二步 使用set方法设置属性值");
    }

    //初始化方法
    public void initMethod(){
        System.out.println("第三步 执行初始化方法");
    }
    public void destroyMethod(){
        System.out.println("第五步 执行销毁方法");
    }
}
