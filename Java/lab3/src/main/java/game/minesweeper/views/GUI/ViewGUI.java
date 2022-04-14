package game.minesweeper.views.GUI;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewGUI {

    public static final double HEIGHT = 768.0;
    private static final double WIDTH = 1024.0;
    private final Stage stage;



    public ViewGUI(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void show() { stage.show(); }

    public void setNewScene(AnchorPane anchorPane) {
        stage.setScene(new Scene(anchorPane, WIDTH, HEIGHT));
        stage.setResizable(false);
    }
}
