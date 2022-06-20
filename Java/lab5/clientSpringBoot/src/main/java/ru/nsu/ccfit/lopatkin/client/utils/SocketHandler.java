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

    public static final String HOST = "25.41.125.221";
    public static final int PORT = 4004;
    public static final String ENDL = "\n";
    public static final String TYPE = "type";
    public static final String DISCONNECT = "disconnect";
    public static final String REQUEST_SEND_OR_CONNECTION_ERROR = "Request send or connection error!";

    private class Listener implements Runnable{
        private String message;
        public Listener(String message) {
            this.message = message;
        }
        private void send() {
                try (Socket socket = new Socket(HOST, PORT);
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    writer.write(message + ENDL);
                    writer.flush();
                    String answer = reader.readLine();
                    processMessage(answer);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
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
        if(json.getString(TYPE).equals(DISCONNECT)) return;
        PostRequest postRequest = postRequestFactory.getGetRequest(json.getString(TYPE));
        postRequest.setStateFromJson(json);
        postRequest.handleRequest();
    }

    public void sendMessage(String message) throws SocketSendMessageException {
        try {
            Thread listenerThread = new Thread(new Listener(message));
            listenerThread.start();
        } catch (Exception e) {
            throw new SocketSendMessageException(REQUEST_SEND_OR_CONNECTION_ERROR);
        }
    }
}
