package com.cczyWyc.task.task_05.concurrent_01;

/**
 * runnable main
 *
 * @author wangyc
 */
public class RunnableMain {
    public static void main(String[] args) {
        RunnableA runnableA = new RunnableA();
        Thread threadA = new Thread(runnableA);

        RunnableB runnableB = new RunnableB();
        Thread threadB = new Thread(runnableB);

        threadA.start();
        threadB.start();

        threadB.interrupt();
        System.out.println(Thread.activeCount());

        Thread.currentThread().getThreadGroup().list();
        System.out.println(Thread.currentThread().getThreadGroup().getParent().activeCount());
        Thread.currentThread().getThreadGroup().getParent().list();
    }
}
