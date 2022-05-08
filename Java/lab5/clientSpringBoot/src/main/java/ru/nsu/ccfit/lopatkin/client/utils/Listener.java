package ru.nsu.ccfit.lopatkin.client.utils;


import ru.nsu.ccfit.lopatkin.client.PostRequests.PostRequest;

import java.io.*;
import java.net.Socket;

public class Listener implements Runnable{

    private Socket socket;

    private BufferedWriter writer;
    private BufferedReader reader;

    private SocketHandler socketHandler;

    @Override
    public void run() {
        try {
            socket = new Socket("",0/* TODO socket params from property? */);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            // TODO logging
        }
        // TODO logging
        try {
            while (socket.isConnected()) {
                String message = reader.readLine();
                // TODO handler
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(PostRequest request) {

    }

    public void setHandler(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }
}
