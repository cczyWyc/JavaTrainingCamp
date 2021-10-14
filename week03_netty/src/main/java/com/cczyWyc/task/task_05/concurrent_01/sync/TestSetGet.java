package com.cczyWyc.task.task_05.concurrent_01.sync;

/**
 * set get synchronized
 *
 * @author wangyc
 */
public class TestSetGet {
    public static void main(String[] args) throws InterruptedException {
        final SetGet sg = new SetGet();
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(sg.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        t.setName("thread-wangyc");
        long start = System.currentTimeMillis();
        sg.set(10);
        System.out.println("......." + (System.currentTimeMillis() - start));
    }

    public static class SetGet {
        int a = 0;
        public synchronized void set(int v) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " setting " + v);
            Thread.sleep(1000);
            a = v;
            System.out.println(Thread.currentThread().getName() + " set " + v);
        }

        public synchronized int get() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " getting ");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " get ");
            return a;
        }
    }
}
