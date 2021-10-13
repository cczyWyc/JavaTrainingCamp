package com.cczyWyc.task.task_05.concurrent_01;

/**
 * Runnable
 *
 * @author wangyc
 */
public class RunnableB implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("RunnableB--------------" + i);
        }
        boolean result1 = Thread.currentThread().isInterrupted();
        boolean result2 = Thread.interrupted();
        boolean result3 = Thread.currentThread().isInterrupted();

        System.out.println("RunnableB run ========> result1:" + result1);
        System.out.println("RunnableB run ========> result2:" + result2);
        System.out.println("RunnableB run ========> result3:" + result3);
    }
}
