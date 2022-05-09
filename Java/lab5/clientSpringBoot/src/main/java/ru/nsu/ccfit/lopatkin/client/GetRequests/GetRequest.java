package ru.nsu.ccfit.lopatkin.client.GetRequests;

import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;

import java.util.List;

public interface GetRequest {

    void handleRequest() throws SocketSendMessageException;

    String convertToJsonString();

    void setState(List<String> args);

    GetRequestType getType();
}
