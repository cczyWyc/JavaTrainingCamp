package com.cczyWyc.task.task_05.concurrent_01.sync;

/**
 * synchronized dmeo
 *
 * @author wangyc
 */
public class Thread3 {

    class Inner {
        private void m4t1() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void m4t2() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //object lock
    private void m4t1(Inner inner) {
        synchronized (inner) {
            inner.m4t1();
        }
    }

    private void m4t2(Inner inner) {
        inner.m4t2();
    }

    public static void main(String[] args) {
        final Thread3 thread = new Thread3();
        final Inner inner = thread.new Inner();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread.m4t1(inner);
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread.m4t2(inner);
            }
        }, "t2");
        t2.start();
        t1.start();
    }
}
