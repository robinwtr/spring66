package com.atguigu.rest.bean;

public class Employee {

    private Integer id;
    private String lastname;
    private Integer gender;
    private String email;

    public Employee() {}

    public Employee(Integer id, String lastname, String email, Integer gender) {
        this.id = id;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                '}';
    }
}
