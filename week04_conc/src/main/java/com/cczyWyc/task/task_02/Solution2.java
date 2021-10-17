package com.cczyWyc.task.task_02;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * thread pool
 *
 * @author wangyc
 */
public class Solution2 {
    private static int core = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        MyThread myThread = new MyThread(2, 3);

        ExecutorService executorService = new ThreadPoolExecutor(core, core, 1000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2048));
        Future<?> future = executorService.submit(myThread::run);
        try {
            //get result. main exit
            System.out.println(future.get());
            System.out.println("time:" + (System.currentTimeMillis() - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
