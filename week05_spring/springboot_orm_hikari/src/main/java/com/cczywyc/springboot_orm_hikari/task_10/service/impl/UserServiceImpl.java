package com.cczywyc.springboot_orm_hikari.task_10.service.impl;

import com.cczywyc.springboot_orm_hikari.task_10.entity.User;
import com.cczywyc.springboot_orm_hikari.task_10.repository.UserRepository;
import com.cczywyc.springboot_orm_hikari.task_10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyc
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
