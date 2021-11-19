package com.cczywyc.task.test;

import com.cczywyc.task.entity.User;
import com.cczywyc.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * main
 *
 * @author wangyc
 */
@Component
public class Main implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User();
        user.setId(3);
        user.setName("wkc");
        userService.insert(user);
    }
}