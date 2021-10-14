package com.cczyWyc.task.task_05.concurrent_01.sync;

/**
 *
 *
 * @author wangyc
 */
public class Counter {
    private int sum = 0;
    private Object lock = new Object();

    public void incr() {
        synchronized (lock) {
            sum ++;
        }
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        int loop = 10_0000;
        Counter counter = new Counter();
        for (int i = 0; i < loop; i++) {
            counter.incr();
        }
        //single thread
        System.out.println("single thread" + counter.getSum());

        //multiple threads
        final Counter counter1 = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                counter1.incr();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                counter1.incr();
            }
        });
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("multiple threads" + counter1.getSum());
    }
}
