package com.cczyWyc.task.task_01.concurrent_02.atomic;

import java.util.concurrent.Semaphore;

/**
 * Semaphore demo
 *
 * @author wangyc
 */
public class SemaphoreCounter {
    /** sum */
    private int sum = 0;
    /** read semaphore */
    private Semaphore readSemaphore = new Semaphore(100, true);
    /** write semaphore */
    private Semaphore writeSemaphore = new Semaphore(1);

    public int incrAndGet() {
        try {
            writeSemaphore.acquireUninterruptibly();
            return ++sum;
        } finally {
            writeSemaphore.release();
        }
    }

    public int getSum() {
        try {
            readSemaphore.acquireUninterruptibly();
            return sum;
        } finally {
            readSemaphore.release();
        }
    }
}
