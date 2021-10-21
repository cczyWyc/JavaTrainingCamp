package com.cczyWyc.task.task_01.concurrent_02.atomic;

/**
 * @author wangyc
 */
public class AtomicMain {
    public static void main(String[] args) {
        //final SyncCount syncCount = new SyncCount();
        //final AtomicCount atomicCount = new AtomicCount();
        final Count count = new Count();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //syncCount.add();
                    //atomicCount.add();
                    count.add();
                }
            }).start();
        }
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("num=" + syncCount.getNum());
        //System.out.println("num=" + atomicCount.getNum());
        System.out.println("num=" + count.getNum());
    }
}
