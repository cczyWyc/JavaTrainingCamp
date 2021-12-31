package com.cczyWyc.task.task_05.concurrent_01;

/**
 * multiple thread update same variable
 * also can use lock
 *
 * @author wangyc
 */
public class ConcurrentDemo {

    int count = 10;

    public void sale() {
        synchronized (this) {
            while (count != 0) {
                System.out.println(Thread.currentThread().getName() + "-" + count);
                count--;
            }
        }
    }

    public static void main(String[] args) {
        ConcurrentDemo concurrentDemo = new ConcurrentDemo();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                concurrentDemo.sale();
            }
        }, "A");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                concurrentDemo.sale();
            }
        }, "B");
        thread1.start();
        thread2.start();
    }
}
