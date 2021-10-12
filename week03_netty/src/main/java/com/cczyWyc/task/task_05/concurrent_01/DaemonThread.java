package com.cczyWyc.task.task_05.concurrent_01;

/**
 * DaemonThread
 *
 * @author wangyc
 */
public class DaemonThread {
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread t = Thread.currentThread();
            System.out.println("current thread is:" + t.getName());
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread");
        thread.setDaemon(true);
        thread.start();

        System.out.println("111111111111111111" + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
