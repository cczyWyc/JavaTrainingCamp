package com.cczyWyc.task.task_01;

/**
 * @author wangyc
 */
public class Hello_2 {
    public static void main(String[] args) {
        Operation operation = new Operation();
        int a = 2;
        int b = 4;
        operation.submit(a);
        operation.submit(b);
        double avg = operation.getAvg();
    }
}
