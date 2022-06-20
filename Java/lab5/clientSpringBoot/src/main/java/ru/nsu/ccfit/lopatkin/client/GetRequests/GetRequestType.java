package ru.nsu.ccfit.lopatkin.client.GetRequests;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public enum GetRequestType {
    LOG_IN(Constants.LOGIN),
    SIGN_UP(Constants.SIGNUP),
    NEW_MESSAGE(Constants.NEW_MESSAGE1),
    ASK_MESSAGE(Constants.ASK_MESSAGE1),
    ONLINE_USER_LIST(Constants.ONLINE_USER_LIST1),
    DISCONNECT(Constants.DISCONNECT1);

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

    private static class Constants {
        public static final String LOGIN = "login";
        public static final String SIGNUP = "signup";
        public static final String NEW_MESSAGE1 = "new_message";
        public static final String ASK_MESSAGE1 = "ask_message";
        public static final String ONLINE_USER_LIST1 = "online_user_list";
        public static final String DISCONNECT1 = "disconnect";
    }
}
