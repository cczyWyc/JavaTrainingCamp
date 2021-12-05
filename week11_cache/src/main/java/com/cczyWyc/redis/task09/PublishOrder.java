package com.cczyWyc.redis.task09;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;

/**
 * order publish
 *
 * @author wangyc
 */
public class PublishOrder {

    /**
     * init
     * @param jedisPool jedisPool
     * @param publishName publish name
     */
    public PublishOrder(JedisPool jedisPool, String publishName) {
        System.out.println("Start publisher order-----");

        Jedis jedis = jedisPool.getResource();
        int orderId = 0;
        for (int i = 0; i < 10; i++) {
            orderId = new Random().nextInt(10) + 1;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            jedis.publish(publishName, "order number is:" + orderId);
        }
        //when the message is empty, end
        jedis.publish(publishName, "");
    }
}
