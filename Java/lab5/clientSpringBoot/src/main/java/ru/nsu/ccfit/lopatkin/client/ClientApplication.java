package ru.nsu.ccfit.lopatkin.client;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ClientApplication.class, args);
        Application.launch(JavaFXApplication.class, args);
    }

}
