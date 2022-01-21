package com.cczyWyc.task.task_01.concurrent_03.threadLocl;

/**
 * ThreadLocal demo
 *
 * @author wangyc
 */
public class ThreadLocalDemo {
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        public Integer initiaValue() {
            return 0;
        }
    };

    public ThreadLocal<Integer> getThreadLocal() {
        return seqNum;
    }

    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();

        SnThread client1 = new SnThread(threadLocalDemo);
        SnThread client2 = new SnThread(threadLocalDemo);
        SnThread client3 = new SnThread(threadLocalDemo);

        client1.start();
        client2.start();
        client3.start();
    }

    public static class SnThread extends Thread {
        private ThreadLocalDemo sn;

        public SnThread(ThreadLocalDemo sn) {
            this.sn = sn;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() + "] ---> sn [" + sn.getNextNum() + "]");
            }
            sn.getThreadLocal().remove();
        }
    }
}
