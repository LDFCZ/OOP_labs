package game.minesweeper;

import game.minesweeper.models.GameModel;
import game.minesweeper.views.GUI.MenuViewGUI;
import game.minesweeper.views.GUI.ViewGUI;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainGUI extends Application {

    public static final String MINESWEEPER = "Minesweeper";

    @Override
    public void start(Stage stage) {
        GameModel gameModel = new GameModel();
        stage.setTitle(MINESWEEPER);
        ViewGUI view = new MenuViewGUI(stage, gameModel);
    }

    public static void main(String[] args) {
        launch();
    }
}