package ru.nsu.ccfit.lopatkin.server.threadPool;

import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;
import ru.nsu.ccfit.lopatkin.server.threadPool.WorkerRunnable;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPooledServer implements Runnable{

    public static final String ERROR_ACCEPTING_CLIENT_CONNECTION = "Error accepting client connection";
    public static final String ERROR_CLOSING_SERVER = "Error closing server";
    public static final String CANNOT_OPEN_PORT_4004 = "Cannot open port 4004";
    protected int serverPort   = 4004;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped    = false;
    protected Thread runningThread= null;
    protected ExecutorService threadPool =
            Executors.newFixedThreadPool(10);

    private final MessageContext messageContext;
    private final SessionContext sessionContext;

    public ThreadPooledServer(MessageContext messageContext, SessionContext sessionContext){
        this.messageContext = messageContext;
        this.sessionContext = sessionContext;
    }

    public void run(){
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    break;
                }
                throw new RuntimeException(
                        ERROR_ACCEPTING_CLIENT_CONNECTION, e);
            }
            this.threadPool.execute(
                    new WorkerRunnable(clientSocket, messageContext, sessionContext));
        }
        this.threadPool.shutdown();
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(ERROR_CLOSING_SERVER, e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException(CANNOT_OPEN_PORT_4004, e);
        }
    }
}
