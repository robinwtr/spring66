package com.atguigu.spring66;

public class Order {
    private String oname;
    private String address;

    public Order(String address, String oname) {
        this.address = address;
        this.oname = oname;
    }

    public void toOrder() {
        System.out.println(oname + " " + address);
    }
}
