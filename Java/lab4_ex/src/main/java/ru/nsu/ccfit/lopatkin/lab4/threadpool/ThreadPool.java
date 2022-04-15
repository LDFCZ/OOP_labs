package ru.nsu.ccfit.lopatkin.lab4.threadpool;

import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.tasks.Task;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.Set;

@Slf4j
public class ThreadPool {

    private final String threadPoolName;
    private final ArrayDeque<Task> taskQueue = new ArrayDeque();

    private final Set<PooledThread> availableThreads = new LinkedHashSet<>();

    public ThreadPool(int threadCount, String threadPoolName, String pooledThreadName) {
        this.threadPoolName = threadPoolName;
        for (int i = 0; i < threadCount; i++) {
            availableThreads.add(new PooledThread(pooledThreadName + " " + i, taskQueue));
        }
        for (PooledThread pt: availableThreads) {
            pt.start();
        }
        log.info("Started " + threadCount + " threads in " + threadPoolName);
    }


    public void addTask(Task t) {
        synchronized (taskQueue) {
            taskQueue.add(t);
            taskQueue.notifyAll();
        }
    }

    public void shutdown() {
        for (PooledThread pt : availableThreads) {
            pt.interruptPooledThread();
        }
    }
}
