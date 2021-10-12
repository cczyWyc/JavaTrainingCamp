package com.cczyWyc.task.task_05.concurrent_01;

/**
 * main2
 *
 * @author wangyc
 */
public class ThreadMain2 {
    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(threadB, "Thread name: (" + i + ")");
            t.start();
            System.out.println(t.getName());
            System.out.println(t.isDaemon());
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
