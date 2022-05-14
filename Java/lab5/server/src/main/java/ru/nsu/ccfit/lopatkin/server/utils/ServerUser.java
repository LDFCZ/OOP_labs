package ru.nsu.ccfit.lopatkin.server.utils;

import ru.nsu.ccfit.lopatkin.server.models.User;

public class ServerUser extends User {

    private static final String SERVER = "SERVER";

    public ServerUser() {
        super(SERVER, "");
    }
}
