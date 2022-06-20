package ru.nsu.ccfit.lopatkin.server.models;

import java.util.Date;

public class Message {


    public Message(User user, String text, Date time) {
        this.user = user;
        this.text = text;
        this.time = time;
    }
    private User user;

    private String text;

    private Date time;

    public User getUser() {
        return user;
    }


    public String getText() {
        return text;
    }


    public Date getTime() {
        return time;
    }


}
