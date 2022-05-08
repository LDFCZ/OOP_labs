package ru.nsu.ccfit.lopatkin.client.utils;

import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.net.Socket;

@Service
public class SocketHandler {

    private class Listener extends Thread{
        private Socket socket;

        private BufferedWriter writer;
        private BufferedReader reader;

        private boolean isListen = true;


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
                while (socket.isConnected() && isListen) {
                    String message = reader.readLine();
                    processMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                // TODO logging
            }
        }

        public void send(String message) throws SocketSendMessageException {
            try {
                writer.write(message);
            } catch (IOException e) {
                throw new SocketSendMessageException("Somthing goes wrong while sending");
            }
        }

        public void stopListen() {
            try {
                isListen = false;
                socket.close();
                this.interrupt();
            } catch (IOException e) {
                // TODO process it
            }
        }
    }

    private Listener listenerThread;

    @PostConstruct
    public void init() {
        this.listenerThread = new Listener();
        this.listenerThread.start();
    }


    @PreDestroy
    public void clean() {
        listenerThread.stopListen();
        try {
            listenerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void processMessage(String message) {
        // TODO PostFabric
    }

    public void sendMessage(String message) throws SocketSendMessageException {
        listenerThread.send(message);
    }
}
