package ru.nsu.ccfit.lopatkin.client.GetRequests;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public enum GetRequestType {
    LOG_IN("login"),
    SIGN_UP("signup"),
    NEW_MESSAGE("new_message"),
    ASK_MESSAGE("ask_message"),
    ONLINE_USER_LIST("online_user_list"),
    DISCONNECT("disconnect");

    private String type;
    GetRequestType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    private static final Map<String, GetRequestType> ENUM_MAP;

    static {
        Map<String, GetRequestType> map = new ConcurrentHashMap<>();
        for (GetRequestType instance : GetRequestType.values()) {
            map.put(instance.getType().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static GetRequestType get(String type) {
        return ENUM_MAP.get(type.toLowerCase());
    }
}
