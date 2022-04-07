package ru.nsu.ccfit.lopatkin.lab4.threadpool;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class PooledThread extends Thread {
    AtomicBoolean shutdownFlag = new AtomicBoolean(false);

    private final ArrayDeque<ThreadPoolTask> taskQueue;

    public PooledThread(String name, ArrayDeque<ThreadPoolTask> taskQueue) {
        super(name);
        this.taskQueue = taskQueue;
    }

    public void interruptPooledThread() {
        interrupt();
        shutdownFlag.set(true);
    }

    private void performTask(ThreadPoolTask t) {
        if (t == null) return;
        t.prepare();
        try {
            t.go();
        } catch (InterruptedException e) {
            t.interrupted();
            shutdownFlag.set(true);
            return;
        }
        t.finish();
    }

    public void run() {
        ThreadPoolTask toExecute = null;
        while (!shutdownFlag.get()) {
            synchronized (taskQueue) {
                if (taskQueue.isEmpty()) {
                    try {
                        taskQueue.wait();
                    } catch (InterruptedException e) {
                        break;
                    }
                } else {
                    toExecute = taskQueue.remove();
                }
            }
            performTask(toExecute);
        }
    }
}
