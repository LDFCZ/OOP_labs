package ru.nsu.ccfit.lopatkin.client.GetRequests;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetDisconnectRequestHandler implements GetRequest{
    @Override
    public String convertToJsonString() {
        return null;
    }

    @Override
    public void setState(List<String> args) {

    }

    @Override
    public GetRequestType getType() {
        return null;
    }
}
