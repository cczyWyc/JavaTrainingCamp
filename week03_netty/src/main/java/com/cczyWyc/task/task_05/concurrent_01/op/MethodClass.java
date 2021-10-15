package com.cczyWyc.task.task_05.concurrent_01.op;

/**
 * XXXX
 *
 * @author wangyc
 */
public class MethodClass {
    /** max product number */
    private final int MAX_COUNT = 20;
    /** product number */
    int product = 0;

    public synchronized void product() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " ::::run::::: " + product);
            Thread.sleep(10);
            if (product >= MAX_COUNT) {
                System.out.println("goods is the max number");
                wait();
            } else {
                product ++;
            }
            notifyAll();
        }
    }

    public synchronized void customer() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " ::::run::::: " + product);
            Thread.sleep(10);
            if (product <= 0) {
                System.out.println("no have goods");
                wait();
            } else {
                product --;
            }
            notifyAll();
        }
    }
}
