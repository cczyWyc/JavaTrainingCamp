package com.cczyWyc.task.task_10.jdbc.curd;

import com.cczyWyc.task.task_10.jdbc.entity.User;

import java.util.List;

/**
 * insert/delete/update/select from the mysql table
 *
 * @author wangyc
 */
public interface Curd {
    /**
     * add user into table
     * @param user user
     */
    public void insert(User user);

    /**
     * delete user form table
     * @param userId userId
     */
    public void delete(int userId);

    /**
     * update
     * @param user user
     */
    public void update(int userId, User user);

    /**
     * user userId select from table
     * @param userId userId
     * @return User
     */
    public User getUserById(int userId);

    /**
     * select all data
     * @return all users
     */
    public List<User> getAll();
}
