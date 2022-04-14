package ru.nsu.ccfit.lopatkin.lab4.tasks;

public interface Task {
    void performWork() throws InterruptedException;
    void changeDelay(int newDelay);
}
