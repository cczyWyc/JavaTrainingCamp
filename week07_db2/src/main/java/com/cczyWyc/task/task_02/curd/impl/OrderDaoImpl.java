package com.cczyWyc.task.task_02.curd.impl;

import com.cczyWyc.task.task_02.curd.OrderDao;
import com.cczyWyc.task.task_02.entity.Order;
import com.cczyWyc.task.task_02.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * implements order dao
 *
 * @author wangyc
 */
public class OrderDaoImpl implements OrderDao {
    @Override
    public void insert(List<Order> orders) {
        Connection connection = JDBCUtil.getConnection();
        Statement statement = null;
        for (Order order : orders) {
            try {
                int id = order.getId();
                String sql = "insert into t_order(id,order_no,user_id,status,logistics_no,total_no,amount_pay,amount_real_pay,freight_amount,visit_reject_reason,deleted,pay_time,create_time,update_time)" +
                        "VALUES ( " + order.getId() + "," + order.getOrderNo() + "," + order.getUserId() + "," + order.getStatus() + "," + order.getLogisticsNo() + "," +
                        order.getGoodsNo() + "," + order.getPayMoney() + "," + order.getRealPayMoney() + "," + order.getFreightMoney() + "," + order.getPayPlatformNO() + "," +
                        order.getDeleted() + "," + order.getPayTime() + "," + order.getCreateTime() + "," + order.getUpdateTime() + ")";
                statement = connection.createStatement();
                statement.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtil.close(connection, statement, null);
            }
        }
    }

    @Override
    public void insertBatch(List<Order> orders) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            String sql = "insert into t_order(id,order_no,user_id,status,logistics_no,total_no,amount_pay,amount_real_pay,freight_amount,visit_reject_reason,deleted,pay_time,create_time,update_time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            for (Order order : orders) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setString(2, order.getOrderNo());
                preparedStatement.setInt(3, order.getUserId());
                preparedStatement.setInt(4, order.getStatus());
                preparedStatement.setString(5, order.getLogisticsNo());
                preparedStatement.setInt(6, order.getGoodsNo());
                preparedStatement.setDouble(7, order.getPayMoney());
                preparedStatement.setDouble(8, order.getRealPayMoney());
                preparedStatement.setDouble(9, order.getFreightMoney());
                preparedStatement.setString(10, order.getPayPlatformNO());
                preparedStatement.setInt(11, order.getDeleted());
                preparedStatement.setTimestamp(12, order.getPayTime());
                preparedStatement.setTimestamp(13, order.getCreateTime());
                preparedStatement.setTimestamp(14, order.getUpdateTime());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, null);
        }
    }

    @Override
    public void insertBatchMultiple(List<Order> orders) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            String sql = "insert into t_order(id,order_no,user_id,status,logistics_no,total_no,amount_pay,amount_real_pay,freight_amount,visit_reject_reason,deleted,pay_time,create_time,update_time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setString(2, order.getOrderNo());
                preparedStatement.setInt(3, order.getUserId());
                preparedStatement.setInt(4, order.getStatus());
                preparedStatement.setString(5, order.getLogisticsNo());
                preparedStatement.setInt(6, order.getGoodsNo());
                preparedStatement.setDouble(7, order.getPayMoney());
                preparedStatement.setDouble(8, order.getRealPayMoney());
                preparedStatement.setDouble(9, order.getFreightMoney());
                preparedStatement.setString(10, order.getPayPlatformNO());
                preparedStatement.setInt(11, order.getDeleted());
                preparedStatement.setTimestamp(12, order.getPayTime());
                preparedStatement.setTimestamp(13, order.getCreateTime());
                preparedStatement.setTimestamp(14, order.getUpdateTime());
                preparedStatement.addBatch();
                if (i % 10000 == 0) {
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, null);
        }
    }
}
