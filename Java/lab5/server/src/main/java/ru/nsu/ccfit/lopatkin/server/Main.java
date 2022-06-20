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
    public static final int DELAY = 3000;
    public static final int PERIOD = 4000;
    public static final String EXIT_STR = "q";

    public static void main(String[] args) {
        logger.info("Server Starts!");
        SessionContext sessionContext = new SessionContext();
        MessageContext messageContext = new MessageContext(sessionContext);

        timer.schedule(new TimeOutTask(Thread.currentThread(), timer, sessionContext, messageContext), DELAY, PERIOD);

        Scanner scanner = new Scanner(System.in);

        ThreadPooledServer server = new ThreadPooledServer(messageContext, sessionContext);
        new Thread(server).start();
        logger.info("ThreadPool Starts!");

        while (true) {
            String inputString = scanner.nextLine();
            if (inputString.equals(EXIT_STR)) {
                logger.info("Stopping Server!");
                server.stop();
                timer.cancel();
                logger.info("Server Stopped!");
                break;
            }
        }
    }
}
