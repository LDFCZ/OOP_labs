package ru.nsu.ccfit.lopatkin.client.GetRequests;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import ru.nsu.ccfit.lopatkin.client.utils.Session;
import ru.nsu.ccfit.lopatkin.client.utils.SocketHandler;

import java.util.List;

// TODO do it...............
@Component
public class GetAskMessageRequestHandler implements GetRequest{

    public static final String TYPE = "type";
    public static final String ID = "id";
    public static final String ASK_MESSAGE = "ask_message";
    private Session session;

    private SocketHandler socketHandler;

    @Autowired
    public GetAskMessageRequestHandler (Session session, SocketHandler socketHandler) {
        this.session = session;
        this.socketHandler = socketHandler;
    }

    @Override
    public void handleRequest() throws SocketSendMessageException {
        if(session.getSessionId() == -1) return;
        socketHandler.sendMessage(this.convertToJsonString());
    }

    @Override
    public String convertToJsonString() {
        JSONObject obj = new JSONObject();
        obj.put(TYPE, ASK_MESSAGE);
        obj.put(ID, session.getSessionId());
        return obj.toString();
    }

    @Override
    public void setState(List<String> args) {
        // for some features in the future :) SOLID cries
    }

    @Override
    public GetRequestType getType() {
        return GetRequestType.ASK_MESSAGE;
    }
}
