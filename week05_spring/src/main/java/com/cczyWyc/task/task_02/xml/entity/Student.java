package com.cczyWyc.task.task_02.xml.entity;

/**
 * student entity
 *
 * @author wangyc
 */
public class Student {
    /** student number */
    private Long id;
    /** student name */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
