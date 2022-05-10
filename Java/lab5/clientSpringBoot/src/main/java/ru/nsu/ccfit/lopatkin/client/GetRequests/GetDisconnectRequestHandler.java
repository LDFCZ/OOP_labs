package ru.nsu.ccfit.lopatkin.client.GetRequests;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import ru.nsu.ccfit.lopatkin.client.utils.Session;
import ru.nsu.ccfit.lopatkin.client.utils.SocketHandler;

import java.util.List;

@Component
public class GetDisconnectRequestHandler implements GetRequest{

    private Session session;
    private SocketHandler socketHandler;

    @Override
    public void handleRequest() throws SocketSendMessageException {
        socketHandler.sendMessage(this.convertToJsonString());
    }

    @Override
    public String convertToJsonString() {
        JSONObject obj = new JSONObject();
        obj.put("type", this.getType().getType());
        obj.put("id", session.getSessionId());
        return obj.toString() + "\n";
    }

    @Override
    public void setState(List<String> args) {
        // for some features in the future :)
    }

    @Override
    public GetRequestType getType() {
        return GetRequestType.DISCONNECT;
    }
}
