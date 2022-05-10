package ru.nsu.ccfit.lopatkin.server;

import ru.nsu.ccfit.lopatkin.server.threadPool.ThreadPooledServer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ThreadPooledServer server = new ThreadPooledServer(4004);
        new Thread(server).start();


        while (true) {
            String inputString = scanner.nextLine();
            if (inputString.equals("q")) {
                System.out.println("Stopping Server");
                server.stop();
                break;
            }
        }

    }
}
