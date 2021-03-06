package com.cczyWyc.task.task_02;

/**
 * thread join
 *
 * @author wangyc
 */
public class Solution1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final int[] result = {0};
        MyThread myThread = new MyThread(2, 3);

        Thread thread = new Thread(() -> {
            result[0] = myThread.run();
        });
        thread.setName("myThread---");
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //get result. Main thread exit
        System.out.println(result[0]);
        System.out.println("time:" + (System.currentTimeMillis() - start));
    }
}
