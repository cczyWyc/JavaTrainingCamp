package com.cczyWyc.task.task_01.concurrent_03.tool;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangyc
 */
public class Increment implements Runnable {

    /**
     * CountDownLatch
     */
    CountDownLatch countDownLatch;

    public Increment(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("await");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiter Released");
    }
}
