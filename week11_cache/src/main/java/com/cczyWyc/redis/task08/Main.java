package com.cczyWyc.redis.task08;

/**
 * main
 *
 * @author wangyc
 */
public class Main {
    /** redis lock name and value */
    private static final String LOCK = "redis_redis";
    /** expire time */
    private static final int EXPIRE_TIME = 5;
    /** goods amount */
    private static int amount = 20;

    public static void redisLock() {
        System.out.println("redis lock test start------");

        if (!RedisLock.getInstance().lock(LOCK, EXPIRE_TIME)) {
            System.out.println(String.format("Thread %s get redis lock failed.", Thread.currentThread().getName()));
            return;
        }

        System.out.println(String.format("Thread %s get redis lock successful.", Thread.currentThread().getName()));
        try {
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName());
            amount -= 1;
            System.out.println("goods reduction 1, goods number is:" + amount);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RedisLock.getInstance().releaseLock(LOCK);
        System.out.println("redis lock test end------");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(Main::redisLock);
        Thread thread2 = new Thread(Main::redisLock);
        thread1.setName("redis-test-thread1");
        thread2.setName("redis-test-thread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Thread thread3 = new Thread(Main::redisLock);
        thread3.start();
        thread3.join();
    }
}
