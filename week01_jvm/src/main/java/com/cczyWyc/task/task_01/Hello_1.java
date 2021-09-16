package com.cczyWyc.task.task_01;

/**
 * @author wangyc
 */
public class Hello_1 {
    private static final int[] numbers = {1, 3, 7, 9};

    public static void main(String[] args) {
        for (int number : numbers) {
            if (number > 5) {
                System.out.println("The number is bigger than 5");
            }
        }
    }
}
