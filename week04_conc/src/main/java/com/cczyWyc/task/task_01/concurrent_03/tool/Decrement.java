package com.cczyWyc.task.task_01.concurrent_03.tool;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangyc
 */
public class Decrement implements Runnable {

    /**
     * CountDownLatch
     */
    CountDownLatch countDownLatch;

    public Decrement(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = (int) countDownLatch.getCount(); i > 0; i--) {
            try {
                Thread.sleep(1000);
                System.out.println("count down");
                this.countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
