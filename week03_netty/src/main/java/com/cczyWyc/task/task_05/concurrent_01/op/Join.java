package com.cczyWyc.task.task_05.concurrent_01.op;

/**
 * join demo
 *
 * @author wangyc
 */
public class Join {
    public static void main(String[] args) {
        Object oo = new Object();

        MyThread myThread = new MyThread("thread1 --");
        myThread.setOo(oo);
        myThread.start();

        synchronized (oo) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
                        //The wait() and join() methods are used to pause the current thread. The wait() is used in
                        // with notify() and notifyAll() methods, but join() is used in Java to wait until one thread finishes its execution.
                        //wait() is mainly used for shared resources, a thread notifies other waiting thread when
                        // a resource becomes free. On the other hand join() is used for waiting a thread to die.

                        //oo.wait(0);
                        myThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }
}