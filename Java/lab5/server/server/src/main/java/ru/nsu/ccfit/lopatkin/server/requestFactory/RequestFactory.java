package ru.nsu.ccfit.lopatkin.server.requestFactory;


import org.json.JSONObject;
import ru.nsu.ccfit.lopatkin.server.requestHandlers.RequestHandler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class RequestFactory {

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

    public RequestHandler getRequestHandler(JSONObject object)
            throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Properties requests = new Properties();
        requests.load(RequestFactory.class.getClassLoader().getResourceAsStream("request.properties"));

        if(!requests.containsKey(object.getString("type"))) {
            throw new RuntimeException("No such request: " + object.getString("type"));
        }

        return (RequestHandler) Class.forName((String) requests.get(object.getString("type"))).getConstructor(new Class[] {JSONObject.class}).newInstance(object);
    }

}
