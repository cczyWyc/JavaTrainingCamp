package com.cczyWyc.task.task_01.concurrent_02.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangyc
 */
public class AtomicCount {
    private AtomicInteger num = new AtomicInteger();

    public int add() {
        return num.getAndIncrement();
    }

    public int getNum() {
        return num.get();
    }
}
