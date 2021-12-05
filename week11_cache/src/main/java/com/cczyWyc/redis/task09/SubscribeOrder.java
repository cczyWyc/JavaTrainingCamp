package com.cczyWyc.redis.task09;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * order subscribe
 *
 * @author wangyc
 */
public class SubscribeOrder {

    /**
     * init
     *
     * @param jedisPool jedis pool
     * @param channelName channel name
     */
    public SubscribeOrder(final JedisPool jedisPool, final String channelName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Start subscribe order------");
                Jedis jedis = jedisPool.getResource();
                jedis.subscribe(getSubscriber(), channelName);
            }
        }, "subscribe-thread").start();
    }

    /**
     * get subscriber
     *
     * @return jedis pubsub
     */
    private JedisPubSub getSubscriber() {
        return new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                if (message.isEmpty()) {
                    System.out.println("SubPub end");
                    System.exit(0);
                }
                System.out.printf("Receive message from %s ----- %s\n", channel, message);
            }
        };
    }
}
