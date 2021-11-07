package com.cczywyc.task.repository.slave;

import com.cczywyc.task.entity.slave.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangyc
 */
@Repository("slaveUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
}
