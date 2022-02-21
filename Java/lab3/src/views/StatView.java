package views;

import constspace.CONSTSPACE;
import controllers.MenuController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import utils.GameButton;

import java.util.ArrayList;
import java.util.List;

public class StatView {
    private final Stage stage;
    private final AnchorPane pane;

    private final List<GameButton> buttons;

    private final static int MENU_BUTTON_START_X = 100;
    private final static int MENU_BUTTON_START_Y = 100;


    public StatView (Stage stage) {
        buttons = new ArrayList<>();
        this.pane = new AnchorPane();
        this.stage = stage;
        stage.setScene(new Scene(pane, CONSTSPACE.WIDTH, CONSTSPACE.HEIGHT));

        CreateButtons();
        createBackground();
        stage.show();
    }

    private void addMenuButtons(GameButton button) {
        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + buttons.size() * 100);
        buttons.add(button);
        pane.getChildren().add(button);
    }

    private void CreateButtons() {
        createHomeButton();
    }

    private void createHomeButton() {
        GameButton startButton = new GameButton("BACK");
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                MenuView mv = new MenuView(stage);
            }
        });
    }


    private void createBackground() {
        Image backgroundImage = new Image("/resources/bg.jpg", 0, 0, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        pane.setBackground(new Background(background));
    }
}
