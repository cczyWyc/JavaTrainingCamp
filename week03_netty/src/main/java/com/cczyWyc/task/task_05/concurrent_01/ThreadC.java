package com.cczyWyc.task.task_05.concurrent_01;

import java.util.concurrent.Callable;

/**
 *
 *
 * @author wangyc
 */
public class ThreadC implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(500);
        System.out.println("This is threadC");
        return "ThreadC";
    }
}
