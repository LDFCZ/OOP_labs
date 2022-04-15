package ru.nsu.ccfit.lopatkin.lab4.threadpool;

import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.tasks.Task;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
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
            t.performWork(getName());
        } catch (InterruptedException e) {
            log.info(getName() + " INTERRUPTED!");
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
                        log.info(getName() + " INTERRUPTED!");
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
