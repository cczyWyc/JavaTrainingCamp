package com.cczyWyc.task;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author wangyc
 */
public class Transaction2pcXaRawJdbcExampleApplication {
    public static void main(String[] args) throws SQLException, IOException {
        DataSource dataSource = getShardingDataSource();
        cleanupData(dataSource);

        TransactionTypeHolder.set(TransactionType.XA);

        Connection connection = dataSource.getConnection();
        String sql = "insert into t_order (user_id, order_id) VALUES (?, ?);";

        System.out.println("First XA start insert data");
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            for (int i = 1; i < 11; i++) {
                statement.setLong(1, i);
                statement.setLong(2, i);
                statement.executeUpdate();
            }
            connection.commit();
        }

        System.out.println("First XA insert successful");

        System.out.println("Second XA start insert data");
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            for (int i = 0; i < 11; i++) {
                statement.setLong(1, i + 5);
                statement.setLong(2, i + 5);
                statement.executeUpdate();
            }
            connection.commit();
        } catch (Exception e) {
            System.out.println("Second XA insert failed");
            connection.rollback();
        } finally {
            connection.close();
        }
    }

    private static void cleanupData(DataSource dataSource) {
        System.out.println("Delete all data");
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("delete from t_order;");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * get sharding sphere datasource
     *
     * @return sharding data source
     * @throws SQLException Sql ex
     * @throws IOException IO ex
     */
    static private DataSource getShardingDataSource() throws SQLException, IOException {
        String ymlFileName = "E:\\JavaCode\\JavaTrainingCamp\\week08_db3\\shardingsphere-xa-transaction\\src\\main\\resources\\sharding-databases-tables.yaml";
        File file = new File(ymlFileName);
        return YamlShardingSphereDataSourceFactory.createDataSource(file);
    }
}
