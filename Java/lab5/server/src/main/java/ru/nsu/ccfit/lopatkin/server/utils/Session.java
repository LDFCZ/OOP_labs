package ru.nsu.ccfit.lopatkin.server.utils;

import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;

import ru.nsu.ccfit.lopatkin.server.models.Message;
import ru.nsu.ccfit.lopatkin.server.models.User;

import java.util.Date;
import java.util.List;


public class Session {
    public static final int NEW_MESSAGES_COUNTER = 10;
    private final long id;

    private final User user;

    private final MessageContext messageContext;

    private int newMessagesCounter;

    private boolean activityDuringLastTime = true;

    public Session(MessageContext messageContext, User user, long id) {
        this.messageContext = messageContext;
        this.user = user;
        this.id = id;
        this.newMessagesCounter = NEW_MESSAGES_COUNTER;
    }

    public void updateMessageCounter() {
        newMessagesCounter++;
    }

    public String getUserName() {
        return user.getName();
    }

    public boolean getActivityDuringLastTime() {
        return activityDuringLastTime;
    }

    public void setActivityDuringLastTimeOut() {
        this.activityDuringLastTime =  false;
    }

    public long getId() {
        return id;
    }

    public void putNewMessage(String text, Date time) {
        activityDuringLastTime = true;
        messageContext.addMessage(new Message(user, text, time));
    }

    public List<Message> getNewMessages() {
        activityDuringLastTime = true;
        int c = newMessagesCounter;
        newMessagesCounter = 0;
        return messageContext.getLastMessages(c);
    }
}
