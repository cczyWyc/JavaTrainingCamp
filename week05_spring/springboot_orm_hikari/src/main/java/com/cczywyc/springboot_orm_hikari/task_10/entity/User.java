package com.cczywyc.springboot_orm_hikari.task_10.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user entity
 *
 * @author wangyc
 */
@Data
@Entity
@Table(name = "tbl_user")
public class User {
    /** id */
    @Id
    private int id;

    /** username */
    @Column(name = "name")
    private String username;

    /** password */
    @Column(name = "password")
    private String password;

    /** password */
    @Column(name = "phoneNumber")
    private String phoneNumber;

    /** age */
    @Column(name = "age")
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
