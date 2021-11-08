package com.cczywyc.task.entity.master;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user entity
 *
 * @author wangyc
 */
@Entity
@Table(name = "t1")
public class User {
    @Id
    private int id;
}
