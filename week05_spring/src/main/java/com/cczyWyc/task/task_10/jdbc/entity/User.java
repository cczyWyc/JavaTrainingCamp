package com.cczyWyc.task.task_10.jdbc.entity;

import lombok.Data;

/**
 * user entity
 *
 * @author wangyc
 */
@Data
public class User {
    /** id */
    private int id;
    /** username */
    private String username;
    /** password */
    private String password;
    /** phone number */
    private String phoneNumber;
    /** age */
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
