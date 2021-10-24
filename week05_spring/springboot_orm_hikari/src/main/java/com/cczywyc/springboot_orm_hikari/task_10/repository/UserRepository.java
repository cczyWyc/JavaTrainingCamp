package com.cczywyc.springboot_orm_hikari.task_10.repository;

import com.cczywyc.springboot_orm_hikari.task_10.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wangyc
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
