package com.cczyWyc.task.task_05.concurrent_01.sync;

/**
 * synchronized demo
 *
 * @author wangyc
 */
public class Thread1 implements Runnable {
    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":synchronized loop" + i);
            }
        }
    }

    public static void main(String[] args) {
        Thread1 thread = new Thread1();
        Thread ta = new Thread(thread, "A");
        Thread tb = new Thread(thread, "B");
        ta.start();
        tb.start();
    }
}
