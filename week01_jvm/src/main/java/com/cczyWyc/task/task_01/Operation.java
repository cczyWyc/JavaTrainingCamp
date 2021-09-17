package com.cczyWyc.task.task_01;

/**
 * @author wangyc
 */
public class Operation {
    private int count = 0;
    private double sum = 0.0D;

    public void submit(double value) {
        this.count ++;
        this.sum += value;
    }

    public double getAvg() {
        if (this.count == 0) {
            return sum;
        }
        return this.sum/this.count;
    }
}
