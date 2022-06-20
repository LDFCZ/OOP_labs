package ru.nsu.ccfit.lopatkin.client.PostRequests;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum PostRequestType {
    LOG_IN("login"),
    SIGN_UP("signup"),

    ASK_MESSAGE("ask_message"),
    NEW_MESSAGE("new_message"),
    ONLINE_USER_LIST("online_user_list");

    private String type;
    PostRequestType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    private static final Map<String, PostRequestType> ENUM_MAP;

    static {
        Map<String, PostRequestType> map = new ConcurrentHashMap<>();
        for (PostRequestType instance : PostRequestType.values()) {
            map.put(instance.getType().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static PostRequestType get(String type) {
        return ENUM_MAP.get(type.toLowerCase());
    }
}
