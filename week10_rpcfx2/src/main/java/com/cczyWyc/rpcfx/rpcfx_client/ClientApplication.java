package com.cczyWyc.rpcfx.rpcfx_client;

import com.cczyWyc.rpcfx.rpcfx_api.model.Order;
import com.cczyWyc.rpcfx.rpcfx_api.model.User;
import com.cczyWyc.rpcfx.rpcfx_api.service.OrderService;
import com.cczyWyc.rpcfx.rpcfx_api.service.UserService;
import com.cczyWyc.rpcfx.rpcfx_core.proxy.RpcByteBuddy;
import com.cczyWyc.rpcfx.rpcfx_core.proxy.RpcClientJdk;

/**
 * client app
 *
 * @author wangyc
 */
public class ClientApplication {
    public static void main(String[] args) {
        //proxy
        RpcClientJdk clientJdk = new RpcClientJdk();
        UserService userService = clientJdk.creat(UserService.class, "http://locahost:8080/");
        User user = userService.findById(1);
        if (user == null) {
            System.out.println("Client service invoke failed!");
            return;
        }
        System.out.println("find user id=1 from server:" + user.getName());

        RpcByteBuddy rpcByteBuddy = new RpcByteBuddy();
        OrderService orderService = rpcByteBuddy.creat(OrderService.class, "http://localhost:8080");
        Order order = orderService.findById(2134);
        if (order == null) {
            System.out.println("Client service invoke failed");
            return;
        }
        System.out.println(String.format("find order name=%s, user=%d",order.getName(),order.getUserId()));
    }
}
