package ru.nsu.ccfit.lopatkin.client.GetRequests;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import ru.nsu.ccfit.lopatkin.client.utils.Session;
import ru.nsu.ccfit.lopatkin.client.utils.SocketHandler;

import java.util.Date;
import java.util.List;

@Component
public class GetDisconnectRequestHandler implements GetRequest{

    public static final String TYPE = "type";
    public static final String ID = "id";
    private Session session;
    private SocketHandler socketHandler;

    @Autowired
    public GetDisconnectRequestHandler(Session session, SocketHandler socketHandler) {
        this.session = session;
        this.socketHandler = socketHandler;
    }

    @Override
    public void handleRequest() throws SocketSendMessageException {
        socketHandler.sendMessage(this.convertToJsonString());
    }

    @Override
    public String convertToJsonString() {
        JSONObject obj = new JSONObject();
        obj.put(TYPE, this.getType().getType());
        obj.put(ID, session.getSessionId());
        return obj.toString();
    }

    @Override
    public void setState(List<String> args) {
        // for some features in the future :) SOLID cries ಥ_ಥ
    }

    @Override
    public GetRequestType getType() {
        return GetRequestType.DISCONNECT;
    }
}
