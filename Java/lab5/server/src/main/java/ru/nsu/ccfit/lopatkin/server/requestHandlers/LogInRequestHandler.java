package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;
import ru.nsu.ccfit.lopatkin.server.exceptions.FindUserException;
import ru.nsu.ccfit.lopatkin.server.models.User;
import ru.nsu.ccfit.lopatkin.server.services.UserService;

public class LogInRequestHandler extends RequestHandler{

    public static final String TYPE = "type";
    public static final String LOGIN = "login";
    public static final String NAME = "name";
    public static final String NO_SUCH_USER = "No such user!";
    public static final String PASSWORD = "password";
    public static final String STATUS = "status";
    public static final String OK = "ok";
    public static final String ID = "id";
    public static final String MESSAGE = "message";
    public static final String BAD_PASSWORD = "Bad password!";
    public static final String FAILED = "failed";

    public LogInRequestHandler(JSONObject jsonObject, MessageContext messageContext, SessionContext sessionContext) {
        super(jsonObject, messageContext, sessionContext);
    }
    @Override
    public String handleRequest() {
        JSONObject obj = new JSONObject();
        obj.put(TYPE, LOGIN);
        UserService userService = new UserService();
        try {
            User user = userService.findUserByName(jsonObject.getString(NAME));
            if (user == null) throw new FindUserException(NO_SUCH_USER);
            if (user.getPassword().equals(jsonObject.getString(PASSWORD))) {
                obj.put(STATUS, OK);
                obj.put(ID, sessionContext.addNewSession(messageContext, user));
                obj.put(MESSAGE, "");
            }
            else throw new FindUserException(BAD_PASSWORD);
        } catch (FindUserException e) {
            obj.put(STATUS, FAILED);
            obj.put(ID, -1);
            obj.put(MESSAGE, e.getMessage());
        }

        return obj.toString();
    }
}
