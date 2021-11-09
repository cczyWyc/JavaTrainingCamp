package com.cczywyc.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * DataSource config
 *
 * @author wangyc
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.cczywyc.task.repository",
        transactionManagerRef = "transactionManager",
        entityManagerFactoryRef = "entityManager")
@EnableTransactionManagement
@DependsOn("dataSourceRouting")
public class DataSourceConfig {

    /** dataSourceRouting */
    @Autowired
    private DataSourceRouting dataSourceRouting;

    @Bean
    @Primary
    public DataSource dataSource() {
        return dataSourceRouting;
    }

    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource())
                .packages("com.cczywyc.task.entity")
                .build();
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(
            @Autowired @Qualifier("entityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean.getObject()));
    }
}
