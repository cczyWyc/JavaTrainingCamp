package com.cczyWyc.task.task_05.concurrent_01;

/**
 * implements Runnable
 *
 * @author wangyc
 */
public class ThreadB implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("This is thread B");

        Thread currentThread = Thread.currentThread();
        String currentThreadName = currentThread.getName();

        System.out.println("The thread name is:" + currentThreadName);
        System.out.println("The current thread " + currentThreadName + " active thread number is " + currentThread.getThreadGroup().activeCount());
        System.out.println("The thread " + currentThreadName + " id is " + currentThread.getId());
        System.out.println("The thread " + currentThreadName + " priority  is " + currentThread.getPriority());
        System.out.println("The thread " + currentThreadName + " state is " + currentThread.getState());
        System.out.println("The thread " + currentThreadName + " thread group is " + currentThread.getThreadGroup());
        System.out.println("The thread " + currentThreadName + " is alive " + currentThread.isAlive());
        System.out.println("The thread " + currentThreadName + " is daemon thread " + currentThread.isDaemon());
    }
}
