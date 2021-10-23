package com.cczyWyc.task.task_10.jdbc.curd.impl;

import com.cczyWyc.task.task_10.jdbc.curd.Curd;
import com.cczyWyc.task.task_10.jdbc.entity.User;
import com.cczyWyc.task.task_10.jdbc.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * implement Curd
 * @author wangyc
 */
public class CurdImpl implements Curd {
    @Override
    public void insert(User user) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "insert into tbl_user values (?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, preparedStatement, null);
        }
    }

    @Override
    public void delete(int userId) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "delete from tbl_user where id=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, preparedStatement, null);
        }
    }

    @Override
    public void update(int userId, User user) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update tbl_user set name=?,password=?,phoneNumber=?,age=? where id=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setInt(5, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, preparedStatement, null);
        }
    }

    @Override
    public User getUserById(int userId) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select  * from tbl_user where id=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("name"));
                user.setPassword(resultSet.getNString("password"));
                user.setPhoneNumber(resultSet.getNString("phoneNumber"));
                user.setAge(resultSet.getInt("age"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select  * from tbl_user";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("name"));
                user.setPassword(resultSet.getNString("password"));
                user.setPhoneNumber(resultSet.getNString("phoneNumber"));
                user.setAge(resultSet.getInt("age"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, preparedStatement, resultSet);
        }
        return null;
    }
}
