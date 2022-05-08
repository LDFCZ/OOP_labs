package com.example.test2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Test2ApplicationTests {

    @Autowired
    User1 user1;

    @Autowired
    User2 user2;

    @Test
    void contextLoads() {
        user1.test();
        user2.test();
        user1.test2();
    }

}
