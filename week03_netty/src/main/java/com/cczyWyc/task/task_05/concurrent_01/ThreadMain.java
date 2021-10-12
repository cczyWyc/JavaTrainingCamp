package com.cczyWyc.task.task_05.concurrent_01;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * main
 *
 * @author wangyc
 */
public class ThreadMain {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.start();
        System.out.println("ThreadA...........");

        ThreadB threadB = new ThreadB();
        new Thread(threadB).start();
        System.out.println("ThreadB................");

        ThreadC threadC = new ThreadC();
        FutureTask<String> futureTask = new FutureTask<>(threadC);
        new Thread(futureTask).start();
        System.out.println("ThreadC.....................");
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
