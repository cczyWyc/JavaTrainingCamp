package com.cczywyc.task.service.impl;

import com.cczywyc.task.entity.master.User;
import com.cczywyc.task.repository.master.UserRepository;
import com.cczywyc.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyc
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("masterUserRepository")
    UserRepository masterUserRepository;

    @Autowired
    @Qualifier("slaveUserRepository")
    com.cczywyc.task.repository.slave.UserRepository slaveUserRepository;

    @Override
    public void insert(User user) {
        masterUserRepository.save(user);
    }

    @Override
    public List<com.cczywyc.task.entity.slave.User> getAllUsers() {
        return slaveUserRepository.findAll();
    }
}
