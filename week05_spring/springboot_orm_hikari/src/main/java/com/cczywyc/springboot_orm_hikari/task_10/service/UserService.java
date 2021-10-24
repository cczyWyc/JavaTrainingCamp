package com.cczywyc.springboot_orm_hikari.task_10.service;

import com.cczywyc.springboot_orm_hikari.task_10.entity.User;

import java.util.List;

/**
 * user service
 *
 * @author wangyc
 */
public interface UserService {

    /**
     * get all users from table
     * @return all users of a list
     */
    List<User> getAllUsers();
}
