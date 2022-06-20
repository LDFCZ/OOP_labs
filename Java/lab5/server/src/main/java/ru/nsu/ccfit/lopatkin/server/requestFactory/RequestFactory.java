package ru.nsu.ccfit.lopatkin.server.requestFactory;


import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.contexts.MessageContext;
import ru.nsu.ccfit.lopatkin.server.contexts.SessionContext;
import ru.nsu.ccfit.lopatkin.server.requestHandlers.RequestHandler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class RequestFactory {

    public static final String REQUEST_PROPERTIES = "request.properties";
    public static final String TYPE = "type";
    public static final String NO_SUCH_REQUEST = "No such request: ";
    private static volatile RequestFactory requestFactory;

    public static RequestFactory getInstance() {
        if (requestFactory == null) {
            synchronized (RequestFactory.class) {
                if (requestFactory == null) {
                    requestFactory = new RequestFactory();
                }
            }
        }
        return requestFactory;
    }

    public RequestHandler getRequestHandler(JSONObject object, MessageContext messageContext, SessionContext sessionContext)
            throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Properties requests = new Properties();
        requests.load(RequestFactory.class.getClassLoader().getResourceAsStream(REQUEST_PROPERTIES));

        if(!requests.containsKey(object.getString(TYPE))) {
            throw new RuntimeException(NO_SUCH_REQUEST + object.getString(TYPE));
        }

        return (RequestHandler) Class.forName((String) requests.get(object.getString(TYPE)))
                .getConstructor(new Class[] {JSONObject.class, MessageContext.class, SessionContext.class})
                .newInstance(object, messageContext, sessionContext);
    }

}
