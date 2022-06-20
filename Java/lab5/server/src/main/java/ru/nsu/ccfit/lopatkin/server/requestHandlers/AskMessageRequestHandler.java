package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.consts.Consts;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;
import ru.nsu.ccfit.lopatkin.server.models.Message;

import java.text.SimpleDateFormat;
import java.util.List;

public class AskMessageRequestHandler extends RequestHandler{


    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String ASK_MESSAGE = "ask_message";
    public static final String NAME = "name";
    public static final String TEXT = "text";
    public static final String TIME = "time";
    public static final String MESSAGES = "messages";
    public static final String COUNT = "count";

    public AskMessageRequestHandler(JSONObject jsonObject, MessageContext messageContext, SessionContext sessionContext) {
        super(jsonObject, messageContext, sessionContext);
    }

    @Override
    public String handleRequest() {
        SimpleDateFormat formatter = new SimpleDateFormat(Consts.PATTERN);

        long id = jsonObject.getLong(ID);

        List<Message> messages = sessionContext.getSessionById(id).getNewMessages();
        int count = messages.size();
        JSONObject obj = new JSONObject();
        obj.put(TYPE, ASK_MESSAGE);

        if (messages.size() != 0) {
            JSONArray jsonMessages = new JSONArray();
            for (Message message: messages) {
                if(message.getUser().getName().equals(sessionContext.getSessionById(id).getUserName())) {
                    count--;
                    continue;
                }
                JSONObject m = new JSONObject();
                m.put(NAME, message.getUser().getName());
                m.put(TEXT,message.getText());
                m.put(TIME, formatter.format(message.getTime()));
                jsonMessages.put(m);
            }
            obj.put(MESSAGES,jsonMessages);

        }
        obj.put(COUNT, count);
       return obj.toString();
    }
}
