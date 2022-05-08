package com.example.test2;

import com.example.test2.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class User2 {
    @Autowired
    private Session session;

    public void test() {
        System.out.println(session.getId());
        session.setId(2);
        System.out.println(session.getId());
    }
}
