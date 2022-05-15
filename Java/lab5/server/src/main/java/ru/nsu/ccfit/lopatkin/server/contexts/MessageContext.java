package ru.nsu.ccfit.lopatkin.server.contexts;

import ru.nsu.ccfit.lopatkin.server.models.Message;


import java.util.LinkedList;
import java.util.List;

public class MessageContext {

    public static final int MAX_SIZE = 100;
    private final List<Message> messageList = new LinkedList<>();

    private final SessionContext sessionContext;

    public MessageContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public synchronized void addMessage(Message message) {
        if (messageList.size() > MAX_SIZE) messageList.remove(0);
        messageList.add(message);
        sessionContext.updateAllMessageCounters();
    }

    public List<Message> getLastMessages(int count) {
        return messageList.subList(count < messageList.size() ? messageList.size() - count : 0 , messageList.size());
    }
}
