package ru.nsu.ccfit.lopatkin.client.GetRequests;

import org.springframework.stereotype.Component;

import java.util.List;

// TODO do it...............
@Component
public class GetOnlineUserListRequestHandler implements GetRequest{
    @Override
    public void handleRequest() {

    }

    @Override
    public String convertToJsonString() {
        return null;
    }

    @Override
    public void setState(List<String> args) {

    }

    @Override
    public GetRequestType getType() {
        return GetRequestType.ONLINE_USER_LIST;
    }
}
