package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;

import java.util.List;

public class OnlineUserListRequestHandler extends RequestHandler{
    public OnlineUserListRequestHandler(JSONObject jsonObject, MessageContext messageContext, SessionContext sessionContext) {
        super(jsonObject, messageContext, sessionContext);
    }
    @Override
    public String handleRequest() {
        JSONArray array = sessionContext.getActiveUsersArray();
        JSONObject obj = new JSONObject();
        obj.put("type", "online_user_list");
        obj.put("list", array);
        return obj.toString();
    }
}
