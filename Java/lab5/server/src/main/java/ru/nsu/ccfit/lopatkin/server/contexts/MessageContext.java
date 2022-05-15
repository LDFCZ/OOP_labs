package ru.nsu.ccfit.lopatkin.server.contexts;

import ru.nsu.ccfit.lopatkin.server.models.Message;
import ru.nsu.ccfit.lopatkin.server.utils.Session;

import java.util.LinkedList;
import java.util.List;

public class MessageContext {

    private final List<Message> messageList = new LinkedList<>();

    private final SessionContext sessionContext;

    public MessageContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public synchronized void addMessage(Message message) {
        if (messageList.size() > 100) messageList.remove(0);
        messageList.add(message);
        sessionContext.updateAllMessageCounters();
    }

    public List<Message> getLastMessages(int count) {
        return messageList.subList(count < messageList.size() ? messageList.size() - count : 0 , messageList.size());
    }
}
