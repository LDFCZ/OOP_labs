package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;

public abstract class RequestHandler {

    protected final JSONObject jsonObject;
    protected final MessageContext messageContext;
    protected final SessionContext sessionContext;

    public RequestHandler(JSONObject jsonObject, MessageContext messageContext, SessionContext sessionContext) {
        this.jsonObject = jsonObject;
        this.messageContext = messageContext;
        this.sessionContext = sessionContext;
    }

    public abstract String handleRequest();
}
