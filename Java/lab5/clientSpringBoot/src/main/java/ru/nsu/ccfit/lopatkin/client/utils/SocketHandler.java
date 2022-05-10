package ru.nsu.ccfit.lopatkin.client.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.client.PostRequests.PostRequest;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.client.factories.PostRequestFactory;

import java.io.*;
import java.net.Socket;

@Service
public class SocketHandler {

    private class Listener implements Runnable{
        private String message;
        public Listener(String message) {
            this.message = message;
        }
        private void send() {
                try {
                    Socket socket = new Socket("25.41.125.221", 4004);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer.write(message);
                    writer.flush();
                    String answer = reader.readLine();
                    processMessage(answer);
                    socket.close();
                } catch (IOException e) {
                    // TODO logging
                }
                // TODO logging
        }

        @Override
        public void run() {
            send();
        }
    }

    private PostRequestFactory postRequestFactory;

    @Autowired
    public SocketHandler(@Lazy PostRequestFactory postRequestFactory) {
        this.postRequestFactory = postRequestFactory;
    }




    private void processMessage(String message) {
        JSONObject json = new JSONObject(message);
        if(json.getString("type").equals("disconnect")) return;
        PostRequest postRequest = postRequestFactory.getGetRequest(json.getString("type"));
        postRequest.setStateFromJson(json);
        postRequest.handleRequest();
    }

    public void sendMessage(String message) throws SocketSendMessageException {
        try {
            Thread listenerThread = new Thread(new Listener(message));
            listenerThread.start();
        } catch (Exception e) {
            throw new SocketSendMessageException("Request send or connection error!");
        }
    }
}
