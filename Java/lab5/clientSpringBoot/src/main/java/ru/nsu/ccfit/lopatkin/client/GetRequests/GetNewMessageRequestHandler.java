package ru.nsu.ccfit.lopatkin.client.GetRequests;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import ru.nsu.ccfit.lopatkin.client.utils.Session;
import ru.nsu.ccfit.lopatkin.client.utils.SocketHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Component
public class GetNewMessageRequestHandler implements GetRequest{
    private Session session;
    private SocketHandler socketHandler;


    private  String text;

    @Autowired
    public GetNewMessageRequestHandler(Session session, SocketHandler socketHandler) {
        this.session = session;
        this.socketHandler = socketHandler;
    }
    @Override
    public void handleRequest() throws SocketSendMessageException {
        socketHandler.sendMessage(this.convertToJsonString());
    }

    @Override
    public String convertToJsonString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        JSONObject obj = new JSONObject();
        obj.put("type", "new_message");
        obj.put("id", session.getSessionId());
        obj.put("date", formatter.format(new Date()));
        obj.put("text", text);
        return obj.toString();
    }

    @Override
    public void setState(List<String> args) { // text
        text = args.get(0);
    }

    @Override
    public GetRequestType getType() {
        return GetRequestType.NEW_MESSAGE;
    }
}
