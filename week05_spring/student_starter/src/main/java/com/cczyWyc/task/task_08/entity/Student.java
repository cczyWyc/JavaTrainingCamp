package com.cczyWyc.task.task_08.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wangyc
 */
@ConfigurationProperties(prefix = "student.info")
public class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
