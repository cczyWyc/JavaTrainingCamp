package com.cczyWyc.task.task_05.concurrent_01.op;

/**
 * xxxxx
 *
 * @author wangyc
 */
class MyThread extends Thread {
    private String name;
    private Object oo;

    public MyThread(String name) {
        this.name = name;
    }

    public void setOo(Object oo) {
        this.oo = oo;
    }

    @Override
    public void run() {
        synchronized (oo) {
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }
        }
    }
}
