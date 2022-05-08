package ru.nsu.ccfit.lopatkin.client.utils;

import org.springframework.stereotype.Service;

@Service
public class Session {

    private long sessionId;

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }
}
