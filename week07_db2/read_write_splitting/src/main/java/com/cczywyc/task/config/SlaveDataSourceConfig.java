package com.cczywyc.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangyc
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryMaster",
        transactionManagerRef = "transactionManagerMaster",
        basePackages = {"com.cczywyc.task.repository.slave"}
)
public class SlaveDataSourceConfig {
    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource dataSource;
    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;


    @Bean("entityManagerSlave")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return Objects.requireNonNull(localContainerEntityManagerFactoryBean(builder).getObject()).createEntityManager();
    }

    @Bean("entityManagerFactorySlave")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource)
                .properties(getVendorProperties())
                //设置实体类所在目录
                .packages("com.study.jpa.model.slaver")
                //持久化单元名称，当存在多个EntityManagerFactory时，需要制定此名称
                .persistenceUnit("slavePersistenceUnit")
                .build();
    }

    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(),
                new HibernateSettings()
        );
    }

    @Bean("transactionManagerSlave")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean(builder).getObject()));
    }
}
