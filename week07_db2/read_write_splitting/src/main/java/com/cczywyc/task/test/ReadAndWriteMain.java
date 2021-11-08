package com.cczywyc.task.test;

import com.cczywyc.task.entity.slave.User;
import com.cczywyc.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * main
 *
 * @author wangyc
 */
@Component
public class ReadAndWriteMain implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*User user = new User();
        user.setId(1);
        user.setName("cczyWyc");
        userService.insert(user);*/

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.getId() + user.getName());
        }
    }
}
