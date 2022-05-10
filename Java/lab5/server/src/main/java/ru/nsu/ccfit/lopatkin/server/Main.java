package ru.nsu.ccfit.lopatkin.server;

import ru.nsu.ccfit.lopatkin.server.dao.UserDao;
import ru.nsu.ccfit.lopatkin.server.models.User;
import ru.nsu.ccfit.lopatkin.server.threadPool.ThreadPooledServer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);

        //ThreadPooledServer server = new ThreadPooledServer(4004);
        //new Thread(server).start();


        //while (true) {
        //    String inputString = scanner.nextLine();
        //    if (inputString.equals("q")) {
        //        System.out.println("Stopping Server");
        //        server.stop();
        //        break;
        //    }
        //}
        User u1 = new User();
        u1.setName("test");
        u1.setPassword("test");

        User u2 = new User();
        u1.setName("test1");
        u1.setPassword("test1");

        UserDao userDao = new UserDao();

        userDao.save(u1);
        userDao.save(u2);
    }
}
