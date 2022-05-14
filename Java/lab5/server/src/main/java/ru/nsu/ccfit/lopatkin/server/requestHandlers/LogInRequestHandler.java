package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;
import ru.nsu.ccfit.lopatkin.server.exceptions.FindUserException;
import ru.nsu.ccfit.lopatkin.server.models.User;
import ru.nsu.ccfit.lopatkin.server.services.UserService;

public class LogInRequestHandler extends RequestHandler{
    public LogInRequestHandler(JSONObject jsonObject, MessageContext messageContext, SessionContext sessionContext) {
        super(jsonObject, messageContext, sessionContext);
    }
    @Override
    public String handleRequest() {
        JSONObject obj = new JSONObject();
        obj.put("type", "login");
        UserService userService = new UserService();
        try {
            User user = userService.findUserByName(jsonObject.getString("name"));
            if (user.getPassword().equals(jsonObject.getString("password"))) {
                obj.put("status", "ok");
                obj.put("id", sessionContext.addNewSession(messageContext, user));
                obj.put("message", "");
            }
            else throw new FindUserException("Bad password!");
        } catch (FindUserException e) {
            obj.put("status", "failed");
            obj.put("id", -1);
            obj.put("message", e.getMessage());
        }

        return obj.toString();
    }
}
