package game.minesweeper.views.GUI;

import game.minesweeper.utils.GameButton;
import game.minesweeper.utils.GameSubScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MenuViewGUI<MenuController> extends GUIView {

    private final AnchorPane anchorPane = new AnchorPane();

    private final List<GameButton> buttons;
    //private final MenuControllerGUI menuController;

    private final static int MENU_BUTTON_START_X = 100;
    private final static int MENU_BUTTON_START_Y = 100;

    private GameSubScene sceneToHide;
    private GameSubScene helpSubscene;
    private GameSubScene homeSubscene;

    public MenuViewGUI(Stage stage) {
        super(stage);

        buttons = new ArrayList<>();
        createHomeButton();
        setNewScene(anchorPane);
        getStage().show();
    }

    private void addMenuButtons(GameButton button) {
        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + buttons.size() * 100);
        buttons.add(button);
        anchorPane.getChildren().add(button);
    }

    private void createHomeButton() {
        GameButton startButton = new GameButton("HOME");
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

            }
        });
    }
}
