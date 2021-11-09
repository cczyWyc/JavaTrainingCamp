package com.cczywyc.task.test;

import com.cczywyc.task.entity.User;
import com.cczywyc.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangyc
 */
@Component
public class Main implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*User user = new User();
        user.setId(2);
        user.setName("nsb");
        userService.insert(user);*/

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.getId() + ":" + user.getName());
        }
    }
}
