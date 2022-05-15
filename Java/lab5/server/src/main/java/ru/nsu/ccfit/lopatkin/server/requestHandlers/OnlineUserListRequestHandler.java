package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;


public class OnlineUserListRequestHandler extends RequestHandler{

    public static final String TYPE = "type";
    public static final String ONLINE_USER_LIST = "online_user_list";
    public static final String LIST = "list";

    public OnlineUserListRequestHandler(JSONObject jsonObject, MessageContext messageContext, SessionContext sessionContext) {
        super(jsonObject, messageContext, sessionContext);
    }
    @Override
    public String handleRequest() {
        JSONArray array = sessionContext.getActiveUsersArray();
        JSONObject obj = new JSONObject();
        obj.put(TYPE, ONLINE_USER_LIST);
        obj.put(LIST, array);
        return obj.toString();
    }
}
