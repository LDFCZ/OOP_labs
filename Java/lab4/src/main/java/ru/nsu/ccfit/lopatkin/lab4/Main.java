package ru.nsu.ccfit.lopatkin.lab4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import ru.nsu.ccfit.lopatkin.lab4.GUI.views.FactoryView;
import ru.nsu.ccfit.lopatkin.lab4.factory.FactoryCreator;

public class Main extends Application {
    private FactoryCreator factoryCreator;

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);

        factoryCreator = new FactoryCreator();

        FactoryView view = new FactoryView(stage, factoryCreator);
    }

    @Override
    public void stop() {
        factoryCreator.stopFactory();
        Platform.exit();
    }

    public static void main(String[] args) {
        launch();
    }
}