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
}
