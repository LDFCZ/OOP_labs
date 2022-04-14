package game.minesweeper;

import game.minesweeper.models.GameModel;
import game.minesweeper.utils.SIS.Stage;
import game.minesweeper.views.SIS.MenuViewSIS;

import java.io.IOException;

public class MainSIS {

    public static final String SOMETHING_GOES_WRONG = "something goes wrong.......";

    public static void main(String[] args) {
        Stage stage = new Stage();
        GameModel gameModel = new GameModel();
        MenuViewSIS view = new MenuViewSIS(stage, gameModel);
        try {
            stage.launch();
        } catch (IOException e) {
            System.out.print(SOMETHING_GOES_WRONG);
        }
    }
}
