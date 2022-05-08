package ru.nsu.ccfit.lopatkin.client.PostRequests;

import org.springframework.stereotype.Component;

@Component
public class PostOnlineUserListRequestHandler implements PostRequest{
    @Override
    public void setStateFromJson(String JsonString) {

    }

    @Override
    public void handleRequest() {

    }

    @Override
    public PostRequestType getType() {
        return null;
    }
}
