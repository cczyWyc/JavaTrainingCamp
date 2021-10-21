package com.cczyWyc.task.task_01.concurrent_02.atomic;

/**
 * @author wangyc
 */
public class Count {
    private int num = 0;

    public int add() {
        return num++;
    }

    public int getNum() {
        return num;
    }
}
