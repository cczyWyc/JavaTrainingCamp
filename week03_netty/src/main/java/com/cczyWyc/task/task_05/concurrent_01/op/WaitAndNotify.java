package com.cczyWyc.task.task_05.concurrent_01.op;

/**
 * wait and notify demo
 *
 * @author wangyc
 */
public class WaitAndNotify {
    public static void main(String[] args) {
        MethodClass thread = new MethodClass();
        Thread t1 = new Thread(() -> {
            try {
                thread.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                thread.customer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
