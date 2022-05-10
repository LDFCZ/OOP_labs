package ru.nsu.ccfit.lopatkin.client.GetRequests;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import ru.nsu.ccfit.lopatkin.client.utils.SocketHandler;

import java.util.List;

@Component
public class GetLogInRequestHandler implements GetRequest{

    private SocketHandler socketHandler;

    private String name;
    private String password;

    @Autowired
    public GetLogInRequestHandler(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public void handleRequest() throws SocketSendMessageException {
        this.socketHandler.sendMessage(this.convertToJsonString());
    }

    @Override
    public String convertToJsonString() {
        JSONObject obj = new JSONObject();
        obj.put("type", this.getType().getType());
        obj.put("name", name);
        obj.put("password", password);
        return obj.toString() + "\n";
    }

    @Override
    public void setState(List<String> args) {
        // args = {"name", "password"}
        if (args.size() != 2) throw new RuntimeException("Bad Args");
        this.name = args.get(0);
        this.password = args.get(1);
    }

    @Override
    public GetRequestType getType() {
        return GetRequestType.LOG_IN;
    }
}
