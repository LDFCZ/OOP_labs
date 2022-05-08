package com.example.test2;

import com.example.test2.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User1 {

    @Autowired
    private Session session;

    public void test() {
        session.setId(1);
        System.out.println(session.getId());
    }
    public void test2() {
        System.out.println(session.getId());
    }
}
