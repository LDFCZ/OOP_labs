package ru.nsu.ccfit.lopatkin.server.threadPool;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;
import ru.nsu.ccfit.lopatkin.server.requestFactory.RequestFactory;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;


public class WorkerRunnable implements Runnable{

    private static final Logger logger = Logger.getLogger(WorkerRunnable.class);
    public static final String TYPE = "type";
    protected Socket clientSocket = null;

    private final MessageContext messageContext;
    private final SessionContext sessionContext;

    public WorkerRunnable(Socket clientSocket, MessageContext messageContext, SessionContext sessionContext) {
        this.clientSocket = clientSocket;
        this.messageContext = messageContext;
        this.sessionContext = sessionContext;
    }

    public void run() {
        try (BufferedReader input  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter output =  new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
            long time = System.currentTimeMillis();

            JSONObject obj = new JSONObject(input.readLine());
            try {

                logger.info("get " + obj.getString(TYPE));
                output.write(RequestFactory.getInstance().getRequestHandler(obj, messageContext, sessionContext).handleRequest() + "\n");
                output.flush();
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                     InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            logger.info("answered " + obj.getString(TYPE) + " in " + (System.currentTimeMillis() - time) + " ms!");
        } catch (Exception e) {
            logger.error("Worker socket ex - " + e.getMessage());
            e.printStackTrace();
        }
    }
}
