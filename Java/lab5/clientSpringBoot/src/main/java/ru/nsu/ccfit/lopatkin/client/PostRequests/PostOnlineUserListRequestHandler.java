package ru.nsu.ccfit.lopatkin.client.PostRequests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.consts.Consts;
import ru.nsu.ccfit.lopatkin.client.controllers.ChatController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class PostOnlineUserListRequestHandler implements PostRequest{

    public static final String LIST = "list";
    public static final String SPACE = " ";
    public static final String ONLINE_USERS = "ONLINE USERS";
    private ChatController chatController;

    @Autowired
    public  PostOnlineUserListRequestHandler(ChatController chatController) {
        this.chatController = chatController;
    }

    private JSONArray array;

    @Override
    public void setStateFromJson(JSONObject jsonObject) {
        array = jsonObject.getJSONArray(LIST);
    }

    @Override
    public void handleRequest() {
        //StringBuilder usersList = new StringBuilder();
        List<String> users = new ArrayList<>();
        for (int i = 0; i < array.length(); i++)
            users.add(array.getString(i));
            //usersList.append(array.getString(i)).append(SPACE);

        //SimpleDateFormat formatter = new SimpleDateFormat(Consts.TIME_FORMAT);
        //chatController.addMessage(ONLINE_USERS, usersList.toString(), formatter.format(new Date()), false);
        chatController.addOnlineUsers(users);
    }

    @Override
    public PostRequestType getType() {
        return PostRequestType.ONLINE_USER_LIST;
    }
}
