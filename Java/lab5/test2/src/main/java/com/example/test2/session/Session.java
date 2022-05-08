package com.example.test2.session;

import org.springframework.stereotype.Service;

@Service
public class Session {

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
