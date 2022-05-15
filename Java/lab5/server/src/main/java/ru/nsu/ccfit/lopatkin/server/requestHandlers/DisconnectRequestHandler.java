package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;

public class DisconnectRequestHandler extends RequestHandler{

    public static final String TYPE = "type";
    public static final String DISCONNECT = "disconnect";
    public static final String ID = "id";

    public DisconnectRequestHandler(JSONObject jsonObject, MessageContext messageContext, SessionContext sessionContext) {
        super(jsonObject, messageContext, sessionContext);
    }
    @Override
    public String handleRequest() {
        sessionContext.removeSession(sessionContext.getSessionById(jsonObject.getLong(ID)));
        JSONObject obj = new JSONObject();
        obj.put(TYPE, DISCONNECT);
        return obj.toString();
    }
}
