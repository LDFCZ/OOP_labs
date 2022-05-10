package ru.nsu.ccfit.lopatkin.client.PostRequests;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.controllers.LogInController;
import ru.nsu.ccfit.lopatkin.client.utils.Session;

@Component
public class PostLogInRequestHandler implements PostRequest{

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
        this.status = jsonObject.getString("status");
        this.message = jsonObject.getString("message");
        this.id = jsonObject.getLong("id");
    }

    @Override
    public void handleRequest() {
        if (status.equals("ok")) {
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
