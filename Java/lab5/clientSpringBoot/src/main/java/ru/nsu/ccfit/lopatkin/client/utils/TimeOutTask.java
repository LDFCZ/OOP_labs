package ru.nsu.ccfit.lopatkin.client.utils;

import java.util.Timer;
import java.util.TimerTask;

public class TimeOutTask extends TimerTask {
    private TimeOutHandler timeOutHandler;
    private Timer timer;
    private Thread thread;

    public TimeOutTask(Thread thread,  Timer timer, TimeOutHandler timeOutHandler) {
        this.thread = thread;
        this.timeOutHandler = timeOutHandler;
        this.timer = timer;
    }

    @Override
    public void run() {
        if(thread != null && thread.isAlive()) {
            timeOutHandler.handle();
            timer.cancel();
        }
        else timer.cancel();
    }
}