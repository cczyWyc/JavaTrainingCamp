package com.cczyWyc.task.task_02;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 *
 * @author wangyc
 */
public class Solution5 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final int[] result = {0};
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MyThread myThread = new MyThread(2, 3);
        Thread thread = new Thread(() -> {
            result[0] = myThread.run();
            countDownLatch.countDown();
        });
        thread.setName("myThread--");
        thread.start();
        countDownLatch.await();
        System.out.println(result[0]);
        System.out.println("time:" + (System.currentTimeMillis() - start));
    }
}
