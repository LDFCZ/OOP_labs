package ru.nsu.ccfit.lopatkin.client.PostRequests;

public interface PostRequest {
    void setStateFromJson(String JsonString);

    void handleRequest();

    PostRequestType getType();
}
