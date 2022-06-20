package ru.nsu.ccfit.lopatkin.client.utils;


import ru.nsu.ccfit.lopatkin.client.GetRequests.GetRequest;
import ru.nsu.ccfit.lopatkin.client.GetRequests.GetRequestType;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import ru.nsu.ccfit.lopatkin.client.factories.GetRequestFactory;

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
        GetRequest getRequestAskMessage = getRequestFactory.getGetRequest(GetRequestType.ASK_MESSAGE);
        GetRequest getRequestAskOnlineUsers = getRequestFactory.getGetRequest(GetRequestType.ONLINE_USER_LIST);
        try {
            getRequestAskMessage.handleRequest();
            getRequestAskOnlineUsers.handleRequest();
        } catch (SocketSendMessageException e) {
            System.out.println(e.getMessage());
        }
    }
}
