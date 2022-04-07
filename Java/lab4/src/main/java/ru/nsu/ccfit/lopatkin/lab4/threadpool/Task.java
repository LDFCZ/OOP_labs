package ru.nsu.ccfit.lopatkin.lab4.threadpool;

public interface Task {
    String getTaskName();
    void performWork() throws InterruptedException;
    void changeDelay(int newDelay);
}
