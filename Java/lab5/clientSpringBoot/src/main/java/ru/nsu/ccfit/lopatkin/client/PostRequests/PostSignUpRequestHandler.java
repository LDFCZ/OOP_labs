package ru.nsu.ccfit.lopatkin.client.PostRequests;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.controllers.SignUpController;
import ru.nsu.ccfit.lopatkin.client.utils.Session;

@Component
public class PostSignUpRequestHandler implements PostRequest{

    private Session session;

    private String message;
    private String status;
    private long id;

    private SignUpController signUpController;


    @Autowired
    public  PostSignUpRequestHandler(SignUpController signUpController, Session session) {
        this.signUpController = signUpController;
        this.session = session;
    }

    @Override
    public void setStateFromJson(JSONObject jsonObject) {
        this.status = jsonObject.getString("status");
        this.message = jsonObject.getString("message");
        this.id = jsonObject.getLong("id");
    }

    @Override
    public void handleRequest() {
        if (status.equals("ok")) {
            signUpController.setAuthorized();
            session.setSessionId(id);
            return;
        }
        signUpController.setBadData(message);
    }

    @Override
    public PostRequestType getType() {
        return PostRequestType.SIGN_UP;
    }
}
