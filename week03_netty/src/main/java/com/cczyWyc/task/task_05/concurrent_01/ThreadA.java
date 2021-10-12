package com.cczyWyc.task.task_05.concurrent_01;

/**
 * extends thread
 *
 * @author wangyc
 */
public class ThreadA extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("This is thread A");
    }
}
