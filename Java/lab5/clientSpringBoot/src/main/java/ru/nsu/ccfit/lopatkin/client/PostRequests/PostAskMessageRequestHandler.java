package ru.nsu.ccfit.lopatkin.client.PostRequests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.controllers.ChatController;

@Component
public class PostAskMessageRequestHandler implements PostRequest{

    private ChatController chatController;

    @Autowired
    public PostAskMessageRequestHandler(ChatController chatController) {
        this.chatController = chatController;
    }

    private int count;
    private JSONArray array;

    @Override
    public void setStateFromJson(JSONObject jsonObject) {
        count = jsonObject.getInt("count");
        if (count != 0)
            array = jsonObject.getJSONArray("messages");
    }

    @Override
    public void handleRequest() {
        if (count > 0) {
            for (Object obj : array) {
                JSONObject message = new JSONObject(obj.toString());
                chatController.addMessage(message.getString("name"), message.getString("text"), message.getString("time"), false);
            }
        }
    }

    @Override
    public PostRequestType getType() {
        return PostRequestType.ASK_MESSAGE;
    }
}
