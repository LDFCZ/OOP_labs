package ru.nsu.ccfit.lopatkin.client.GetRequests;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.consts.Consts;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import ru.nsu.ccfit.lopatkin.client.utils.Session;
import ru.nsu.ccfit.lopatkin.client.utils.SocketHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Component
public class GetNewMessageRequestHandler implements GetRequest{
    public static final String TYPE = "type";
    public static final String NEW_MESSAGE = "new_message";
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String TEXT = "text";
    public static final int TEXT_ARG = 0;
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
        SimpleDateFormat formatter = new SimpleDateFormat(Consts.TIME_FORMAT);
        JSONObject obj = new JSONObject();
        obj.put(TYPE, NEW_MESSAGE);
        obj.put(ID, session.getSessionId());
        obj.put(DATE, formatter.format(new Date()));
        obj.put(TEXT, text);
        return obj.toString();
    }

    @Override
    public void setState(List<String> args) { // text
        text = args.get(TEXT_ARG);
    }

    @Override
    public GetRequestType getType() {
        return GetRequestType.NEW_MESSAGE;
    }
}
