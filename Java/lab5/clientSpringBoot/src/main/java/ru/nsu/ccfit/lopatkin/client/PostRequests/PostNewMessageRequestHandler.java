package ru.nsu.ccfit.lopatkin.client.PostRequests;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.controllers.ChatController;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO do it...............
@Component
public class PostNewMessageRequestHandler implements PostRequest{

    private ChatController chatController;

    @Autowired
    public PostNewMessageRequestHandler(ChatController chatController) {
        this.chatController = chatController;
    }

    private String errorMessage;
    private String status;

    @Override
    public void setStateFromJson(JSONObject jsonObject) {
        status = jsonObject.getString("status");
        if(!status.equals("ok")) errorMessage = jsonObject.getString("message");
    }

    @Override
    public void handleRequest() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if(!status.equals("ok")) chatController.addMessage("", errorMessage, formatter.format(new Date()), true);
    }

    @Override
    public PostRequestType getType() {
        return PostRequestType.NEW_MESSAGE;
    }
}
