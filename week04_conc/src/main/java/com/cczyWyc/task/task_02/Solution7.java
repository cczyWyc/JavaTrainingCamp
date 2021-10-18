package com.cczyWyc.task.task_02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock the main thread. when is complete, get the value
 *
 * @author wangyc
 */
public class Solution7 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final int[] result = {0};
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        MyThread myThread = new MyThread(2, 3);
        Thread thread = new Thread(() -> {
            try {
                lock.lock();
                result[0] = myThread.run();
                if (result[0] == 5) {
                    condition.signal();
                }
            } finally {
                lock.unlock();
            }
        });
        thread.setName("myThread--");
        thread.start();
        lock.lock();
        condition.await();
        System.out.println(result[0]);
        System.out.println("time:" + (System.currentTimeMillis() - start));
        lock.unlock();
    }
}
