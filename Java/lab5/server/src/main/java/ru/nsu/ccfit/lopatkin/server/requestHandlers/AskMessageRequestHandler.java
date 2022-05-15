package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;
import ru.nsu.ccfit.lopatkin.server.models.Message;

import java.text.SimpleDateFormat;
import java.util.List;

public class AskMessageRequestHandler extends RequestHandler{
    public AskMessageRequestHandler(JSONObject jsonObject, MessageContext messageContext, SessionContext sessionContext) {
        super(jsonObject, messageContext, sessionContext);
    }

    @Override
    public String handleRequest() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        long id = jsonObject.getLong("id");

        List<Message> messages = sessionContext.getSessionById(id).getNewMessages();
        int count = messages.size();
        JSONObject obj = new JSONObject();
        obj.put("type", "ask_message");

        if (messages.size() != 0) {
            JSONArray jsonMessages = new JSONArray();
            for (Message message: messages) {
                if(message.getUser().getName().equals(sessionContext.getSessionById(id).getUserName())) {
                    count--;
                    continue;
                }
                JSONObject m = new JSONObject();
                m.put("name", message.getUser().getName());
                m.put("text",message.getText());
                m.put("time", formatter.format(message.getTime()));
                jsonMessages.put(m);
            }
            obj.put("messages",jsonMessages);

        }
        obj.put("count", count);
       return obj.toString();
    }
}
