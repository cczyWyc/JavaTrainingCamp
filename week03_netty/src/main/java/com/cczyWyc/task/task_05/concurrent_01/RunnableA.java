package com.cczyWyc.task.task_05.concurrent_01;

/**
 * Runnable
 *
 * @author wangyc
 */
public class RunnableA implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("RunnableA--------------" + i);
        }
    }
}
