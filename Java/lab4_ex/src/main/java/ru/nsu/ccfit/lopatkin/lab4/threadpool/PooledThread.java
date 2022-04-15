package ru.nsu.ccfit.lopatkin.lab4.threadpool;

import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.tasks.Task;

import java.util.ArrayDeque;

@Slf4j
public class PooledThread extends Thread {
    private boolean shutdownFlag = false;

    private final ArrayDeque<Task> taskQueue;


    public PooledThread(String name, ArrayDeque<Task> taskQueue) {
        super(name);
        this.taskQueue = taskQueue;
    }

    public void interruptPooledThread() {
        interrupt();
        shutdownFlag = true;
    }

    private void performTask(Task t) {
        if (t == null) return;
        try {
            t.performWork(getName());
        } catch (InterruptedException e) {
            log.info(getName() + " INTERRUPTED!");
            shutdownFlag = true;
        }
    }

    public void run() {
        Task toExecute = null;
        while (!shutdownFlag) {
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
