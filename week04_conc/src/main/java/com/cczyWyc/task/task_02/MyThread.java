package com.cczyWyc.task.task_02;

/**
 * base class my thread
 *
 * @author wangyc
 */
public class MyThread {
    private int a;
    private int b;
    private int sum;

    public MyThread(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int run() {
        try {
            Thread.sleep(1000);
            synchronized (this) {
                sum = a + b;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread name:" + Thread.currentThread().getName());
        return sum;
    }
}
