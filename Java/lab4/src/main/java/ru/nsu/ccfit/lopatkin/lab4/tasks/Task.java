package ru.nsu.ccfit.lopatkin.lab4.tasks;

public interface Task {
    void performWork(String threadName) throws InterruptedException;
    void changeDelay(int newDelay);
}
