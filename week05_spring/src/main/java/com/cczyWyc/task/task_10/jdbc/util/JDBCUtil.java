package com.cczyWyc.task.task_10.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC util
 *
 * @author wangyc
 */
public class JDBCUtil {
    /** mysql jdbc driver */
    private static final String driver = "com.mysql.cj.jdbc.driver";
    /** connect url */
    private static final String url = "jdbc:mysql://localhost:3306/geektime_demo";
    /** username */
    private static final String username = "root";
    /** password */
    private static final String password = "123456";

    static {
        try {
            Class.forName(JDBCUtil.driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * get jdbc connection
     * @return connection
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.username, JDBCUtil.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * close the jdbc connection
     * @param connection conn
     * @param statement st
     * @param resultSet rs
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
