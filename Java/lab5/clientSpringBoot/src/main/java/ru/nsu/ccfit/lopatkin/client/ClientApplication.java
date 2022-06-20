package ru.nsu.ccfit.lopatkin.client;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }

}
