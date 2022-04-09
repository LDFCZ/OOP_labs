package ru.nsu.ccfit.lopatkin.lab4.threadpool;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.Set;

public class ThreadPool {
    private final ArrayDeque<Task> taskQueue = new ArrayDeque();
    private int maxTaskQueueSize;

    private final Set<PooledThread> availableThreads = new LinkedHashSet<>();

    public ThreadPool(int threadCount) {
        this(threadCount, 100);
    }

    public ThreadPool(int threadCount, int maxQueueSize) {
        for (int i = 0; i < threadCount; i++) {
            availableThreads.add(new PooledThread("Performer_" + i, taskQueue));
        }
        for (PooledThread pt: availableThreads) {
            pt.start();
        }

        this.maxTaskQueueSize = maxQueueSize;
    }


    public void addTask(Task t) {
        synchronized (taskQueue) {
            if (taskQueue.size() >= maxTaskQueueSize) {
                taskQueue.notifyAll();
                return;
            }
            taskQueue.add(t);
            taskQueue.notifyAll();
        }
    }

    public void shutdown() {
        for (PooledThread pt : availableThreads) {
            pt.interruptPooledThread();
            try {
                pt.join();
            } catch (InterruptedException e) {
                // TODO log it or smth
            }
        }
    }

    public Set<PooledThread> getPoledThreads() {
        return availableThreads;
    }
}
