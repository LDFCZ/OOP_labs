package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;
import ru.nsu.ccfit.lopatkin.server.exceptions.SaveUserException;
import ru.nsu.ccfit.lopatkin.server.models.User;
import ru.nsu.ccfit.lopatkin.server.services.UserService;

public class SignUpRequestHandler extends RequestHandler{

    public static final String TYPE = "type";
    public static final String SIGNUP = "signup";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String STATUS = "status";
    public static final String OK = "ok";
    public static final String ID = "id";
    public static final String MESSAGE = "message";
    public static final String FAILED = "failed";

    public SignUpRequestHandler(JSONObject jsonObject, MessageContext messageContext, SessionContext sessionContext) {
        super(jsonObject, messageContext, sessionContext);
    }
    @Override
    public String handleRequest() {
        JSONObject obj = new JSONObject();
        obj.put(TYPE, SIGNUP);
        UserService userService = new UserService();
        try {
            User user = new User(jsonObject.getString(NAME), jsonObject.getString(PASSWORD));
            if(userService.createNewUser(jsonObject.getString(NAME), jsonObject.getString(PASSWORD))) {
                obj.put(STATUS, OK);
                obj.put(ID, sessionContext.addNewSession(messageContext, user));
                obj.put(MESSAGE, "");
            } else throw new SaveUserException(userService.getExceptionMessage());
        } catch (SaveUserException e) {
            obj.put(STATUS, FAILED);
            obj.put(ID, -1);
            obj.put(MESSAGE,e.getMessage());
        }

        return obj.toString();
    }
}
