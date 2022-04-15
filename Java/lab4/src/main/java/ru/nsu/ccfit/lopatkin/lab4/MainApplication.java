package ru.nsu.ccfit.lopatkin.lab4;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
    // TODO magic constants
    // TODO threads interrupting
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }

}
