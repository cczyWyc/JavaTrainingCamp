package com.cczywyc.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user entity
 *
 * @author wangyc
 */
@Entity
@Table(name = "t_student")
public class User {
    /** id */
    @Id
    private int id;

    /** student name */
    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
