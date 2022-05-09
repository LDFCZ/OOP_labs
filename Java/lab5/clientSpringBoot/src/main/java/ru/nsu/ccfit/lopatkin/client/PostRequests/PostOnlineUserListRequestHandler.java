package ru.nsu.ccfit.lopatkin.client.PostRequests;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

// TODO do it...............
@Component
public class PostOnlineUserListRequestHandler implements PostRequest{
    @Override
    public void setStateFromJson(JSONObject jsonObject) {

    }

    @Override
    public void handleRequest() {

    }

    @Override
    public PostRequestType getType() {
        return PostRequestType.ONLINE_USER_LIST;
    }
}
