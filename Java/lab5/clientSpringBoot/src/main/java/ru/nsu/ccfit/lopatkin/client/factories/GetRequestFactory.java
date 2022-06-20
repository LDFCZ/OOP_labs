package ru.nsu.ccfit.lopatkin.client.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.GetRequests.GetRequest;
import ru.nsu.ccfit.lopatkin.client.GetRequests.GetRequestType;

import java.util.EnumMap;
import java.util.List;

@Component
public class GetRequestFactory {
    private EnumMap<GetRequestType, GetRequest> getRequestMap;

    @Autowired
    public GetRequestFactory(List<GetRequest> getRequests) {
        this.getRequestMap = new EnumMap<>(GetRequestType.class);
        for (GetRequest request : getRequests) {
            this.getRequestMap.put(request.getType(), request);
        }
    }

    public GetRequest getGetRequest(GetRequestType getRequestType) {
        return this.getRequestMap.get(getRequestType);
    }

    public GetRequest getGetRequest(String type) {
        return this.getRequestMap.get(GetRequestType.get(type));
    }
}
