package ru.nsu.ccfit.lopatkin.server;


import org.apache.log4j.Logger;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;
import ru.nsu.ccfit.lopatkin.server.threadPool.ThreadPooledServer;
import ru.nsu.ccfit.lopatkin.server.utils.TimeOutTask;

import java.util.Scanner;
import java.util.Timer;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);
    private static final Timer timer = new Timer();
    public static void main(String[] args) {
        logger.info("Server Starts!");
        SessionContext sessionContext = new SessionContext();
        MessageContext messageContext = new MessageContext(sessionContext);

        timer.schedule(new TimeOutTask(Thread.currentThread(), timer, sessionContext, messageContext), 3000, 3000);

        Scanner scanner = new Scanner(System.in);

        ThreadPooledServer server = new ThreadPooledServer(4004, messageContext, sessionContext);
        new Thread(server).start();
        logger.info("ThreadPool Starts!");

        while (true) {
            String inputString = scanner.nextLine();
            if (inputString.equals("q")) {
                logger.info("Stopping Server!");
                server.stop();
                timer.cancel();
                logger.info("Server Stopped!");
                break;
            }
        }
    }
}
