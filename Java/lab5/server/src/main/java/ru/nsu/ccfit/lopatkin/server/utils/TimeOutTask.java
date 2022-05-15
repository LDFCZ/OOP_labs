package ru.nsu.ccfit.lopatkin.server.utils;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;
import ru.nsu.ccfit.lopatkin.server.models.Message;

import java.util.*;

public class TimeOutTask extends TimerTask {
    private static final Logger logger = Logger.getLogger(TimeOutTask.class);
    private final SessionContext sessionContext;

    private final MessageContext messageContext;

    private final Timer timer;
    private final Thread thread;

    public TimeOutTask(Thread thread, Timer timer, SessionContext sessionContext, MessageContext messageContext) {
        this.sessionContext = sessionContext;
        this.thread = thread;
        this.timer = timer;
        this.messageContext = messageContext;
    }

    @Override
    public void run() {
        if(thread != null && thread.isAlive()) {
            System.out.println("TimeOutCollector working!");
            Map<Long, Session> sessionList = sessionContext.getSessionMap();
            Iterator<Map.Entry<Long, Session>> i = sessionList.entrySet().iterator();
           while (i.hasNext()) {
               Map.Entry<Long, Session> session = i.next();
               if (!session.getValue().getActivityDuringLastTime()) {
                   logger.info("session " + session.getValue().getId() + " is closed");
                   sessionContext.removeSession(session.getValue());
                   messageContext.addMessage(new Message(new ServerUser(), session.getValue().getUserName() + " disconnected!", new Date()));
               } else session.getValue().setActivityDuringLastTimeOut();
               System.out.println(session.getValue().getActivityDuringLastTime());
           }
        }
        else timer.cancel();
    }
}
