package ru.nsu.ccfit.lopatkin.server.contexts;

import org.json.JSONArray;
import ru.nsu.ccfit.lopatkin.server.models.User;
import ru.nsu.ccfit.lopatkin.server.utils.Session;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


public class SessionContext {

    private AtomicLong id = new AtomicLong(0);
    private final Map<Long, Session> sessionMap = new LinkedHashMap<>();


    public void updateAllMessageCounters() {
        for (Map.Entry<Long, Session> s : sessionMap.entrySet()) s.getValue().updateMessageCounter();
    }

    public void removeSession(Session sessionToRemove) {
        sessionMap.remove(sessionToRemove.getId());
    }

    public JSONArray getActiveUsersArray() {
        JSONArray array = new JSONArray();
        for (Map.Entry<Long, Session> session : sessionMap.entrySet()) array.put(session.getValue().getUserName());
        return array;
    }

    public Map<Long, Session> getSessionMap() {
        return sessionMap;
    }

    public long addNewSession(MessageContext messageContext, User user) {
        long id = this.id.getAndIncrement();
        sessionMap.put(id, new Session(messageContext, user, id));
        return  id;
    }

    public Session getSessionById(long id) {
        return sessionMap.get(id);
    }
}
