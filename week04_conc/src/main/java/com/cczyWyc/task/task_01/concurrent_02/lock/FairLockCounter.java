package com.cczyWyc.task.task_01.concurrent_02.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * ReentrantLock and fair
 *
 * @author wangyc
 */
public class FairLockCounter {
    /** sum */
    private int sum = 0;
    /** fair lock */
    private Lock lock = new ReentrantLock(true);

    public int addAndGet() {
        try {
            lock.lock();
            return ++sum;
        } finally {
            lock.unlock();
        }
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) {
        FairLockCounter lockCounter = new FairLockCounter();
        int loopNum = 100_1000;
        IntStream.range(0, loopNum).parallel().forEach(i -> lockCounter.addAndGet());
    }
}
