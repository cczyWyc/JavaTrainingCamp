package com.cczyWyc.task.task_02;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier
 *
 * @author wangyc
 */
public class Solution3 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final int[] result = {0};
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(1, () -> {
            countDownLatch.countDown();
        });
        MyThread myThread = new MyThread(2, 3);
        Thread thread = new Thread(() -> {
            try {
                result[0] = myThread.run();
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        thread.setName("myThread--");
        thread.start();
        countDownLatch.await();
        System.out.println(result[0]);
        System.out.println("time:" + (System.currentTimeMillis() - start));
    }
}
