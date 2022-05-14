package ru.nsu.ccfit.lopatkin.client.utils;

import org.springframework.stereotype.Service;

@Service
public class Session {

    private long sessionId;

    private String name;

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
