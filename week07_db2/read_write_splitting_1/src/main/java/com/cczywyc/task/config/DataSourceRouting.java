package com.cczywyc.task.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * data source routing
 *
 * @author wangyc
 */
@Component
public class DataSourceRouting extends AbstractRoutingDataSource {
    /** master datasource config */
    private MasterDataSourceConfig masterDataSourceConfig;

    /** slave datasource config */
    private SlaveDataSourceConfig slaveDataSourceConfig;

    /** datasource context  */
    private DataSourceContextHolder dataSourceContextHolder;

    /**
     * init
     *
     * @param masterDataSourceConfig master datasource config
     * @param slaveDataSourceConfig slave datasource config
     * @param dataSourceContextHolder datasource context
     */
    public DataSourceRouting(MasterDataSourceConfig masterDataSourceConfig,
                             SlaveDataSourceConfig slaveDataSourceConfig,
                             DataSourceContextHolder dataSourceContextHolder) {
        this.masterDataSourceConfig = masterDataSourceConfig;
        this.slaveDataSourceConfig = slaveDataSourceConfig;
        this.dataSourceContextHolder = dataSourceContextHolder;

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceEnum.DATASOURCE_MASTER, masterDataSource());
        dataSourceMap.put(DataSourceEnum.DATASOURCE_SLAVE, slaveDataSource());

        this.setTargetDataSources(dataSourceMap);
        this.setDefaultTargetDataSource(masterDataSource());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceContextHolder.getBranchContext();
    }

    /**
     * set master datasource
     *
     * @return master datasource
     */
    public DataSource masterDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(masterDataSourceConfig.getDriver());
        dataSource.setUrl(masterDataSourceConfig.getUrl());
        dataSource.setUsername(masterDataSourceConfig.getUsername());
        dataSource.setPassword(masterDataSourceConfig.getPassword());
        return dataSource;
    }

    /**
     * set slave datasource
     *
     * @return slave datasource
     */
    public DataSource slaveDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(slaveDataSourceConfig.getDriver());
        dataSource.setUrl(slaveDataSourceConfig.getUrl());
        dataSource.setUsername(slaveDataSourceConfig.getUsername());
        dataSource.setPassword(slaveDataSourceConfig.getPassword());
        return dataSource;
    }
}
