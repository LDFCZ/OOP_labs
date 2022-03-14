package game.minesweeper;

import game.minesweeper.views.MenuViewGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static final String MINESWEEPER = "Minesweeper";

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle(MINESWEEPER);
        new MenuViewGUI(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}