package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;

public class DisconnectRequestHandler extends RequestHandler{
    public DisconnectRequestHandler(JSONObject jsonObject, MessageContext messageContext, SessionContext sessionContext) {
        super(jsonObject, messageContext, sessionContext);
    }
    @Override
    public String handleRequest() {
        sessionContext.removeSession(sessionContext.getSessionById(jsonObject.getLong("id")));
        JSONObject obj = new JSONObject();
        obj.put("type", "disconnect");
        return obj.toString();
    }
}
