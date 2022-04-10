package ru.nsu.ccfit.lopatkin.lab4.threadpool;

import ru.nsu.ccfit.lopatkin.lab4.tasks.Task;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class PooledThread extends Thread {
    AtomicBoolean shutdownFlag = new AtomicBoolean(false);

    private final ArrayDeque<Task> taskQueue;


    public PooledThread(String name, ArrayDeque<Task> taskQueue) {
        super(name);
        this.taskQueue = taskQueue;
    }

    public void interruptPooledThread() {
        interrupt();
        shutdownFlag.set(true);
    }

    private void performTask(Task t) {
        if (t == null) return;
        try {
            t.performWork();
        } catch (InterruptedException e) {
            // TODO interrupted
            shutdownFlag.set(true);
        }
    }

    public void run() {
        Task toExecute = null;
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
