package com.cczywyc.task.service.impl;

import com.cczywyc.task.entity.User;
import com.cczywyc.task.repository.UserRepository;
import com.cczywyc.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyc
 */
@Service
public class UserServiceImpl implements UserService {

    /** repository interface */
    @Autowired
    private UserRepository userRepository;

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
