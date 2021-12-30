package com.cczyWyc.task.task_01.concurrent_02.lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wangyc
 */
public class Count2 {

    /** ReentrantLock */
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void get() {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "-------get begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "-------get end");
            lock.readLock().unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void put() {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "-------put begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "-------put end");
            lock.writeLock().lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
