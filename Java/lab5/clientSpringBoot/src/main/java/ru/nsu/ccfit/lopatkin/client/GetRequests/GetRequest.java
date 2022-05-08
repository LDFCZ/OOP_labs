package ru.nsu.ccfit.lopatkin.client.GetRequests;

import java.util.List;

public interface GetRequest {

    String convertToJsonString();

    void setState(List<String> args);

    GetRequestType getType();
}
