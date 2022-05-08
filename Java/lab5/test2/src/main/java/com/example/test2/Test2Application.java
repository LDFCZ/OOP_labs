package com.example.test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Test2Application {

    @Autowired
    User1 user1;

    @Autowired
    User2 user2;

    public static void main(String[] args) {
        SpringApplication.run(Test2Application.class, args);
        //user1.test();
        //user2.test();
    }

}
