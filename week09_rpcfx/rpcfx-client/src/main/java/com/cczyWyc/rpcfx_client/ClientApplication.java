package com.cczyWyc.rpcfx_client;

import com.cczyWyc.rpcfx_api.model.Order;
import com.cczyWyc.rpcfx_api.model.User;
import com.cczyWyc.rpcfx_api.service.OrderService;
import com.cczyWyc.rpcfx_api.service.UserService;
import com.cczyWyc.rpcfx_core.proxy.RpcByteBuddy;
import com.cczyWyc.rpcfx_core.proxy.RpcClientJdk;

/**
 * client app
 *
 * @author wangyc
 */
public class ClientApplication {
    public static void main(String[] args) {
        //jdk
        RpcClientJdk rpcClientJdk = new RpcClientJdk();
        UserService userService = rpcClientJdk.creat(UserService.class, "http://localhost:8080/");
        User user = userService.findById(1);
        if (user == null) {
            System.out.println("Client service invoke Error");
            return;
        }
        System.out.println("find user id=1 server:" + user.getName());


        //byte buddy
        RpcByteBuddy byteBuddy = new RpcByteBuddy();
        OrderService orderService = byteBuddy.creat(OrderService.class, "http://localhost:8080/");
        Order order = orderService.findById(1221);
        if (order == null) {
            System.out.println("Client service invoke error");
            return;
        }
        System.out.println(String.format("find order name=%s, user=%d",order.getName(),order.getUserId()));
    }
}
