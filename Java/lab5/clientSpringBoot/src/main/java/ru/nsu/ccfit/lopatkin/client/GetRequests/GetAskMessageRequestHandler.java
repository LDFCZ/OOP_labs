package ru.nsu.ccfit.lopatkin.client.GetRequests;

import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;

import java.util.List;

// TODO do it...............
@Component
public class GetAskMessageRequestHandler implements GetRequest{
    @Override
    public void handleRequest() throws SocketSendMessageException {

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
        return GetRequestType.ASK_MESSAGE;
    }
}
