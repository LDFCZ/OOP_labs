package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;

import java.text.SimpleDateFormat;

public class NewMessageRequestHandler extends RequestHandler{

    private final Logger logger = Logger.getLogger(NewMessageRequestHandler.class);
    public NewMessageRequestHandler(JSONObject jsonObject, MessageContext messageContext, SessionContext sessionContext) {
        super(jsonObject, messageContext, sessionContext);
    }
    @Override
    public String handleRequest() {
        JSONObject obj = new JSONObject();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            sessionContext.getSessionById(jsonObject.getLong("id")).putNewMessage(jsonObject.getString("text"), formatter.parse(jsonObject.getString("date")));
            obj.put("type", "new_message");
            obj.put("status", "ok");
        } catch (Exception e) {
            logger.error("data parse error!");
            obj.put("type", "new_message");
            obj.put("status", "error");
            obj.put("message", "SERVER: " + e.getMessage());
        }
        return obj.toString();
    }
}
