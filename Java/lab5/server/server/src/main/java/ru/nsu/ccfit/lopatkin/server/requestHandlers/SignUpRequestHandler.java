package ru.nsu.ccfit.lopatkin.server.requestHandlers;

import org.json.JSONObject;

public class SignUpRequestHandler extends RequestHandler{
    public SignUpRequestHandler(JSONObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public String handleRequest() {
        JSONObject object = new JSONObject();
        object.put("type", "signup");
        object.put("status", "ok");
        object.put("id", 10);
        object.put("message", "");
        return object.toString() + "\n";
    }
}