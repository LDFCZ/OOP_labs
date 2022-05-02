package ru.nsu.ccfit.lopatkin.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.configurations.Session;


@Component
public class Test {

    //@Autowired
    Session session;


    void test(){
        session.setUserId(10);
        System.out.println(session.getUserId());
    }
}
