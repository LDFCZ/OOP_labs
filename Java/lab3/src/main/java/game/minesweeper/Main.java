package game.minesweeper;

import game.minesweeper.views.GUI.MenuViewGUI;
import game.minesweeper.views.GUI.ViewGUI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static final String MINESWEEPER = "Minesweeper";

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle(MINESWEEPER);
        ViewGUI view = new MenuViewGUI(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}