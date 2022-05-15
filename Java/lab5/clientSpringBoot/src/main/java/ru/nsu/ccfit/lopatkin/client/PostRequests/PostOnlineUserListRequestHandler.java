package ru.nsu.ccfit.lopatkin.client.PostRequests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.controllers.ChatController;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class PostOnlineUserListRequestHandler implements PostRequest{

    private ChatController chatController;

    @Autowired
    public  PostOnlineUserListRequestHandler(ChatController chatController) {
        this.chatController = chatController;
    }

    private JSONArray array;

    @Override
    public void setStateFromJson(JSONObject jsonObject) {
        array = jsonObject.getJSONArray("list");
    }

    @Override
    public void handleRequest() {
        StringBuilder usersList = new StringBuilder();
        for (int i = 0; i < array.length(); i++)
            usersList.append(array.getString(i)).append(" ");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        chatController.addMessage("ONLINE USERS", usersList.toString(), formatter.format(new Date()), false);
    }

    @Override
    public PostRequestType getType() {
        return PostRequestType.ONLINE_USER_LIST;
    }
}
