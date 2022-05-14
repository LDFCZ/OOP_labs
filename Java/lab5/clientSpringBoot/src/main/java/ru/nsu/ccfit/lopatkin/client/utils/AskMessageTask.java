package ru.nsu.ccfit.lopatkin.client.utils;

import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.GetRequests.GetRequest;
import ru.nsu.ccfit.lopatkin.client.GetRequests.GetRequestType;
import ru.nsu.ccfit.lopatkin.client.controllers.ChatController;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import ru.nsu.ccfit.lopatkin.client.factories.GetRequestFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class AskMessageTask extends TimerTask {
    private Timer timer;
    private Thread thread;


    private GetRequestFactory getRequestFactory;

    private Session session;

    public AskMessageTask(Thread thread, Timer timer, GetRequestFactory getRequestFactory, Session session) {
        this.thread = thread;
        this.timer = timer;
        this.getRequestFactory = getRequestFactory;
        this.session = session;
    }

    @Override
    public void run() {
        if(thread != null && thread.isAlive()) {
            GetRequest getRequest = getRequestFactory.getGetRequest(GetRequestType.ASK_MESSAGE);
            try {
                getRequest.handleRequest();
            } catch (SocketSendMessageException e) {
                // log it
            }
        }
        else timer.cancel();
    }
}
