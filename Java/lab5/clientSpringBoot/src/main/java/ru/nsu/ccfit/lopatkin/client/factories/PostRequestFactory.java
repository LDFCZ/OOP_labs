package ru.nsu.ccfit.lopatkin.client.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.PostRequests.PostRequest;
import ru.nsu.ccfit.lopatkin.client.PostRequests.PostRequestType;

import java.util.EnumMap;
import java.util.List;

@Component
public class PostRequestFactory {
    private EnumMap<PostRequestType, PostRequest> postRequestMap;

    @Autowired
    public PostRequestFactory(List<PostRequest> postRequests) {
        this.postRequestMap = new EnumMap<>(PostRequestType.class);
        for (PostRequest request : postRequests) {
            this.postRequestMap.put(request.getType(), request);
        }
    }

    public PostRequest getGetRequest(PostRequestType postRequestType) {
        return this.postRequestMap.get(postRequestType);
    }

    public PostRequest getGetRequest(String type) {
        return this.postRequestMap.get(PostRequestType.get(type));
    }
}
