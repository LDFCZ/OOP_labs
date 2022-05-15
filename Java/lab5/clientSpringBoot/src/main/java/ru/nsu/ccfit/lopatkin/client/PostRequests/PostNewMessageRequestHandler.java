package ru.nsu.ccfit.lopatkin.client.PostRequests;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.consts.Consts;
import ru.nsu.ccfit.lopatkin.client.controllers.ChatController;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PostNewMessageRequestHandler implements PostRequest{

    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    public static final String OK = "ok";
    private ChatController chatController;

    @Autowired
    public PostNewMessageRequestHandler(ChatController chatController) {
        this.chatController = chatController;
    }

    private String errorMessage;
    private String status;

    @Override
    public void setStateFromJson(JSONObject jsonObject) {
        status = jsonObject.getString(STATUS);
        if(!status.equals(OK)) errorMessage = jsonObject.getString(MESSAGE);
    }

    @Override
    public void handleRequest() {
        SimpleDateFormat formatter = new SimpleDateFormat(Consts.TIME_FORMAT);
        if(!status.equals(OK)) chatController.addMessage("", errorMessage, formatter.format(new Date()), true);
    }

    @Override
    public PostRequestType getType() {
        return PostRequestType.NEW_MESSAGE;
    }
}
