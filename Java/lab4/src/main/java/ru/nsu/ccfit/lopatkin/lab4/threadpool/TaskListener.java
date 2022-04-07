package ru.nsu.ccfit.lopatkin.lab4.threadpool;

public interface TaskListener {
    void taskInterrupted(Task t);
    void taskFinished(Task t);
    void taskStarted(Task t);
}
