package com.cczywyc.task.repository;


import com.cczywyc.task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangyc
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
}
