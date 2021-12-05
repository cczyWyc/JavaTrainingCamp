package com.cczyWyc.redis.task09;

import redis.clients.jedis.JedisPool;

/**
 * main
 *
 * @author wangyc
 */
public class Main {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("121.40.215.120", 6379);
        String channelName = "cczyWyc";
        new SubscribeOrder(jedisPool, channelName);
        new PublishOrder(jedisPool, channelName);
    }
}
