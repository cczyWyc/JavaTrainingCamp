package com.cczyWyc.redis.task08;


/**
 * simple redis distributed lock
 *
 * @author wangyc
 */
public class RedisLock {

    /**
     * lazy singleton
     */
    private enum EnumSingleton {
        INSTANCE;
        private RedisLock instance;

        EnumSingleton() {
            instance = new RedisLock();
        }

        public RedisLock getSingleton() {
            return instance;
        }
    }

    public static RedisLock getInstance() {
        return EnumSingleton.INSTANCE.getSingleton();
    }

}
