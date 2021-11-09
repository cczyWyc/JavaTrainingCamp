package com.cczywyc.task.service.impl;

import com.cczywyc.task.config.DataSourceContextHolder;
import com.cczywyc.task.config.DataSourceEnum;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataSourceContextHolder dataSourceContextHolder;

    @Override
    public void insert(User user) {
        dataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_MASTER);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        dataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_SLAVE);
        return userRepository.findAll();
    }
}
