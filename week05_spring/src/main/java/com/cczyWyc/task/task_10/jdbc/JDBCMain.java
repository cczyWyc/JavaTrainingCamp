package com.cczyWyc.task.task_10.jdbc;

import com.cczyWyc.task.task_10.jdbc.curd.Curd;
import com.cczyWyc.task.task_10.jdbc.curd.impl.CurdImpl;
import com.cczyWyc.task.task_10.jdbc.entity.User;

/**
 * main function
 *
 * @author wangyc
 */
public class JDBCMain {
    public static void main(String[] args) {
        Curd curd = new CurdImpl();
        User user = new User();
        user.setId(1);
        user.setUsername("cczyWyc");
        user.setPassword("hello");
        user.setPhoneNumber("15222222222");
        user.setAge(18);
        curd.insert(user);
    }
}
