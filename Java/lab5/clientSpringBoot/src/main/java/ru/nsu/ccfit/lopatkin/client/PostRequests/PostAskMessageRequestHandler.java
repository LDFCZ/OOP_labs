package ru.nsu.ccfit.lopatkin.client.PostRequests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.controllers.ChatController;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class PostAskMessageRequestHandler implements PostRequest{

    public static final String COUNT = "count";
    public static final String MESSAGES = "messages";
    public static final String NAME = "name";
    public static final String TEXT = "text";
    public static final String TIME = "time";
    private ChatController chatController;

    @Autowired
    public PostAskMessageRequestHandler(ChatController chatController) {
        this.chatController = chatController;
    }

    private int count;
    private JSONArray array;

    @Override
    public void setStateFromJson(JSONObject jsonObject) {
        count = jsonObject.getInt(COUNT);
        if (count != 0)
            array = jsonObject.getJSONArray(MESSAGES);
    }

    @Override
    public void handleRequest() {
        ArrayList<JSONObject> m = new ArrayList<>();
        if (count > 0) {
            for (Object obj : array) {
                JSONObject message = new JSONObject(obj.toString());
                m.add(message);
                //chatController.addMessage(message.getString(NAME), message.getString(TEXT), message.getString(TIME), false);
            }
            //Collections.reverse(m);
            for (JSONObject jsonObject : m) {
                chatController.addMessage(jsonObject.getString(NAME), jsonObject.getString(TEXT), jsonObject.getString(TIME), false);
            }
        }
    }

    @Override
    public PostRequestType getType() {
        return PostRequestType.ASK_MESSAGE;
    }
}
