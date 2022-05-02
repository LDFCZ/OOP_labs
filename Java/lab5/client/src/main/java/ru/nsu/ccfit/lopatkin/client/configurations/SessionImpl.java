package ru.nsu.ccfit.lopatkin.client.configurations;

import org.springframework.stereotype.Component;

@Component
public class SessionImpl implements Session{
    @Override
    public void setUserId(long id) {
        System.out.println(id);
    }

    @Override
    public long getUserId() {
        return 0;
    }
}
