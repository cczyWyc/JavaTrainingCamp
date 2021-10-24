package com.cczywyc.springboot_orm_hikari.task_10;

import com.cczywyc.springboot_orm_hikari.task_10.entity.User;
import com.cczywyc.springboot_orm_hikari.task_10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author wangyc
 */
@Component
public class HikariAppRunner implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(userService.getAllUsers());
    }
}
