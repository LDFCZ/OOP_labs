package game.minesweeper.utils.GUI;


import game.minesweeper.constspace.FontGetter;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GameButton extends Button {

    public static final double WIDTH = 190;
    public static final double HEIGHT = 49;
    public static final double PRESSED_HEIGHT = 45;
    public static final double SHIFT = 4;
    private static final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('red_button_pressed.png');";
    private static final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('red_button.png');";

    public GameButton(String text) {
        this.setText(text);
        this.setFont(FontGetter.getInstance().getDefaultFont());
        this.setPrefWidth(WIDTH);
        this.setPrefHeight(HEIGHT);
        this.setStyle(BUTTON_FREE_STYLE);
        this.initializeButtonListeners();
    }

    private void setButtonPressedStyle() {
        this.setStyle(BUTTON_PRESSED_STYLE);
        this.setPrefHeight(PRESSED_HEIGHT);
        this.setLayoutY(this.getLayoutY() + SHIFT);
    }

    private void setButtonReleasedStyle() {
        this.setStyle(BUTTON_FREE_STYLE);
        this.setPrefHeight(PRESSED_HEIGHT);
        this.setLayoutY(this.getLayoutY() - SHIFT);
    }

    private void initializeButtonListeners() {
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    GameButton.this.setButtonPressedStyle();
                }

            }
        });
        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    GameButton.this.setButtonReleasedStyle();
                }

            }
        });
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                GameButton.this.setEffect(new DropShadow());
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                GameButton.this.setEffect((Effect)null);
            }
        });
    }
}
