package ru.nsu.ccfit.lopatkin.client.PostRequests;

import org.json.JSONObject;

public interface PostRequest {
    void setStateFromJson(JSONObject jsonObject);

    void handleRequest();

    PostRequestType getType();
}
