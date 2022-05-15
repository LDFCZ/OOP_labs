package ru.nsu.ccfit.lopatkin.client.GetRequests;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import ru.nsu.ccfit.lopatkin.client.utils.Session;
import ru.nsu.ccfit.lopatkin.client.utils.SocketHandler;

import java.util.List;

@Component
public class GetOnlineUserListRequestHandler implements GetRequest{

    public static final String TYPE = "type";
    public static final String ONLINE_USER_LIST = "online_user_list";
    private Session session;
    private SocketHandler socketHandler;


    private  String text;

    @Autowired
    public GetOnlineUserListRequestHandler(Session session, SocketHandler socketHandler) {
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
        obj.put(TYPE, ONLINE_USER_LIST);
        return obj.toString();
    }

    @Override
    public void setState(List<String> args) {

    }

    @Override
    public GetRequestType getType() {
        return GetRequestType.ONLINE_USER_LIST;
    }
}
