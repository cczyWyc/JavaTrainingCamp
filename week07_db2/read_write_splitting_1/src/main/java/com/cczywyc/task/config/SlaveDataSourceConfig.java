package com.cczywyc.task.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * slave datasource config
 *
 * @author wangyc
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource.slave")
public class SlaveDataSourceConfig {
    /** database connection username */
    private String username;

    /** database connection password */
    private String password;

    /** mysql driver */
    @Value("${driver-class-name}")
    private String driver;

    /** jdbc url */
    private String url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
