package ru.nsu.ccfit.lopatkin.client.PostRequests;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.controllers.LogInController;
import ru.nsu.ccfit.lopatkin.client.utils.Session;

@Component
public class PostLogInRequestHandler implements PostRequest{

    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    public static final String ID = "id";
    public static final String OK = "ok";
    private Session session;
    private LogInController logInController;

    @Autowired
    public PostLogInRequestHandler(LogInController logInController, Session session) {
        this.logInController = logInController;
        this.session = session;
    }

    private long id;
    private String status;
    private String message;
    @Override
    public void setStateFromJson(JSONObject jsonObject) {
        this.status = jsonObject.getString(STATUS);
        this.message = jsonObject.getString(MESSAGE);
        this.id = jsonObject.getLong(ID);
    }

    @Override
    public void handleRequest() {
        if (status.equals(OK)) {
            logInController.setAuthorized();
            session.setSessionId(id);
            return;
        }
        logInController.setBadData(message);
    }

    @Override
    public PostRequestType getType() {
        return PostRequestType.LOG_IN;
    }
}
