package com.cczywyc.task.service;

import com.cczywyc.task.entity.master.User;

import java.util.List;

/**
 * @author wangyc
 */
public interface UserService {

    /**
     * insert student
     *
     * @param user
     */
    void insert(User user);

    /**
     * get all users from table
     *
     * @return list of user
     */
    List<com.cczywyc.task.entity.slave.User> getAllUsers();
}
