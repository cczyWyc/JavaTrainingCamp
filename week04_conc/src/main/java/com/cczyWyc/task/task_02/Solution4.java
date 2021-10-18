package com.cczyWyc.task.task_02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * futureTask
 *
 * @author wangyc
 */
public class Solution4 {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        MyThread myThread = new MyThread(2, 3);
        FutureTask futureTask = new FutureTask(myThread::run);
        Thread thread = new Thread(futureTask);
        thread.setName("myThread--");
        thread.start();
        System.out.println(futureTask.get());
        System.out.println("time:" + (System.currentTimeMillis() - start));
    }
}
