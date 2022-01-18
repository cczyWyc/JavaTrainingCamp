package com.cczyWyc.task.task_01.concurrent_03.tool;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch demo
 *
 * @author wangyc
 */
public class TCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Increment increment = new Increment(countDownLatch);
        Decrement decrement = new Decrement(countDownLatch);
        new Thread(increment).start();
        new Thread(decrement).start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

