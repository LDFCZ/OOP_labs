package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.json.JSONObject;

public abstract class RequestHandler {

    protected final JSONObject jsonObject;

    public RequestHandler(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public abstract String handleRequest();
}
