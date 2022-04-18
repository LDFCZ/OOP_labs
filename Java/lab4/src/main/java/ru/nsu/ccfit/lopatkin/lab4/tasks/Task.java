package ru.nsu.ccfit.lopatkin.lab4.tasks;

public interface Task {
    void performWork(String threadName, int delay) throws InterruptedException;
}
