package com.cczyWyc.redis.task08;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

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

    /** jedis poll */
    private JedisPool jedisPool = new JedisPool("121.40.215.120", 6379);
    /** expire time */
    private static final String SET_WITH_EXPIRE_TIME = "EX";
    /** set if not exist */
    private static final String SET_IF_NOT_EXIST = "NX";

    /**
     * redis lock
     *
     * @param lockValue lock value
     * @param seconds lock timeout
     * @return is get the lock
     */
    public boolean lock(String lockValue, int seconds) {
        Jedis jedis = jedisPool.getResource();
        return "OK".equals(jedis.set(lockValue, lockValue, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, seconds));
    }

    /**
     * use setNx to set lock
     *
     * @param lockValue lock value
     * @param seconds lock timeout
     * @return is get the lock
     */
    public boolean lock_setNx(String lockValue, int seconds) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.setnx(lockValue, lockValue);
        // while result = 1, lock success
        if (result == 1L) {
            return jedis.expire(lockValue, seconds) == 1L;
        } else {
            return false;
        }
    }

    /**
     * release redis lock
     *
     * @param lock lock value
     * @return is release redis lock
     */
    public boolean releaseLock(String lock) {
        String luaSctipt = "if redis.call('get',KEYS[1]) == ARGV[1] then " + "return redis.call('del',KEYS[1]) else return 0 end";
        Jedis jedis = jedisPool.getResource();
        return jedis.eval(luaSctipt, Collections.singletonList(lock), Collections.singletonList(lock)).equals(1L);
    }
}
