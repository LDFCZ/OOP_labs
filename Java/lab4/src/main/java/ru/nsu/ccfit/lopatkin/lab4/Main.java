package ru.nsu.ccfit.lopatkin.lab4;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.nsu.ccfit.lopatkin.lab4.views.MainView;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainView view = new MainView(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}