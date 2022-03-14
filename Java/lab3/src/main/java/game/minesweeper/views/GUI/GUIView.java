package game.minesweeper.views.GUI;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public abstract class GUIView {

    public static final double HEIGHT = 768.0;
    private static final double WIDTH = 1024.0;
    private final Stage stage;



    public GUIView(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setNewScene(AnchorPane anchorPane) {
        stage.setScene(new Scene(anchorPane, WIDTH, HEIGHT));
    }
}
