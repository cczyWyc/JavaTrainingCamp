package com.cczyWyc.task.task_03.gateway.outbound.httpclient;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * http client thread factory, thread pool manage thread to request the back-up service
 *
 * @author wangyc
 */
public class HttpClientThreadFactory implements ThreadFactory {
    /** thread group manage */
    private ThreadGroup threadGroup;
    /**
     * daemon thread
     * true：daemon thread
     * false：not daemon thread
     */
    private boolean daemon;
    /** namePrefix */
    private String namePrefix;
    /** AtomicInteger. thread number. atomic */
    private AtomicInteger threadNumber = new AtomicInteger();

    public HttpClientThreadFactory(String namePrefix, boolean daemon) {
        this.daemon = daemon;
        SecurityManager manager = System.getSecurityManager();
        threadGroup = (manager != null) ? manager.getThreadGroup() :Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix;
    }

    public HttpClientThreadFactory(String namePrefix) {
        this(namePrefix, false);
    }

    @Override
    public Thread newThread(@NotNull Runnable r) {
        Thread thread = new Thread(threadGroup, r, namePrefix + "-thread-" + threadNumber.getAndIncrement(), 0);
        thread.setDaemon(daemon);
        return thread;
    }
}
