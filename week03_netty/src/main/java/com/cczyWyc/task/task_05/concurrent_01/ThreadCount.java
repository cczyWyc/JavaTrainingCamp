package com.cczyWyc.task.task_05.concurrent_01;

/**
 * current thread info
 *
 * @author wangyc
 */
public class ThreadCount {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getState());
    }
}
