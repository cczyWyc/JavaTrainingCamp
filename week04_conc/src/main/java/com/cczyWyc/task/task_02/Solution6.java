package com.cczyWyc.task.task_02;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture
 *
 * @author wangyc
 */
public class Solution6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        MyThread myThread = new MyThread(2, 3);
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        Thread thread = new Thread(() -> {
            completableFuture.complete(myThread.run());
        });
        thread.setName("myThread--");
        thread.start();
        System.out.println(completableFuture.get());
        System.out.println("time:" + (System.currentTimeMillis() - start));
    }
}
