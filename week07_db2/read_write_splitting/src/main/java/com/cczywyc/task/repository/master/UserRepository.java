package com.cczywyc.task.repository.master;

import com.cczywyc.task.entity.master.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangyc
 */
@Repository("masterUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
}
