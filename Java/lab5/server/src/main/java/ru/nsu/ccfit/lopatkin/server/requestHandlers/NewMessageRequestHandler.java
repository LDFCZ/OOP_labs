package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.consts.Consts;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;

import java.text.SimpleDateFormat;

public class NewMessageRequestHandler extends RequestHandler{

    public static final String TEXT = "text";
    public static final String DATE = "date";
    public static final String TYPE = "type";
    public static final String NEW_MESSAGE = "new_message";
    public static final String STATUS = "status";
    public static final String OK = "ok";
    public static final String DATA_PARSE_ERROR = "data parse error!";
    public static final String ERROR = "error";
    public static final String MESSAGE = "message";
    public static final String SERVER = "SERVER: ";
    public static final String ID = "id";
    private final Logger logger = Logger.getLogger(NewMessageRequestHandler.class);
    public NewMessageRequestHandler(JSONObject jsonObject, MessageContext messageContext, SessionContext sessionContext) {
        super(jsonObject, messageContext, sessionContext);
    }
    @Override
    public String handleRequest() {
        JSONObject obj = new JSONObject();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(Consts.PATTERN);
            sessionContext.getSessionById(jsonObject.getLong(ID)).putNewMessage(jsonObject.getString(TEXT), formatter.parse(jsonObject.getString(DATE)));
            obj.put(TYPE, NEW_MESSAGE);
            obj.put(STATUS, OK);
        } catch (Exception e) {
            logger.error(DATA_PARSE_ERROR);
            obj.put(TYPE, NEW_MESSAGE);
            obj.put(STATUS, ERROR);
            obj.put(MESSAGE, SERVER + e.getMessage());
        }
        return obj.toString();
    }
}
