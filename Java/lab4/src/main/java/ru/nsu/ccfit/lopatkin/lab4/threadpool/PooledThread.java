package ru.nsu.ccfit.lopatkin.lab4.threadpool;

import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.constSpace.ConstSpace;
import ru.nsu.ccfit.lopatkin.lab4.tasks.Task;

@Slf4j
public class PooledThread extends Thread{
    private boolean shutdownFlag = false;

    private final ThreadPool threadPool;


    public PooledThread(String name, ThreadPool threadPool) {
        super(name);
        this.threadPool = threadPool;
    }

    public void interruptPooledThread() {
        shutdownFlag = true;
        interrupt();
    }

    private void performTask(Task t) {
        try {
            t.performWork(getName(), threadPool.getDelay());
        } catch (InterruptedException e) {
            log.info(getName() + ConstSpace.INTERRUPTED);
            shutdownFlag = true;
        }
    }

    public void run() {
        try {
            while (!shutdownFlag) {
                performTask(threadPool.getTask());
            }
        } catch (InterruptedException e) {
            interruptPooledThread();
            log.info(getName() + ConstSpace.INTERRUPTED);
        }
    }
}
