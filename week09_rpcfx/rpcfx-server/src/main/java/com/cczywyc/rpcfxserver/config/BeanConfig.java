package com.cczywyc.rpcfxserver.config;

import com.cczyWyc.rpcfx_api.service.OrderService;
import com.cczyWyc.rpcfx_api.service.UserService;
import com.cczywyc.rpcfxserver.service.impl.OrderServiceImpl;
import com.cczywyc.rpcfxserver.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangyc
 */
@Configuration
public class BeanConfig {
    @Bean("com.example.demo.service.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean("com.example.demo.service.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
