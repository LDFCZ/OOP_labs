package game.minesweeper.utils;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.*;

public class GameButton extends Button {
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('red_button_pressed.png');";
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('red_button.png');";
    private final String FONT_PATH = "src/main/resources/kenvector_future.ttf";
    private final int FONT_SIZE = 23;

    public GameButton(String text) {

        this.setText(text);
        try {
            this.setFont(Font.loadFont(new FileInputStream(FONT_PATH), FONT_SIZE));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        this.setPrefWidth(190.0D);
        this.setPrefHeight(49.0D);
        this.setStyle(BUTTON_FREE_STYLE);
        this.initializeButtonListeners();
    }

    private void setButtonPressedStyle() {
        this.setStyle(BUTTON_PRESSED_STYLE);
        this.setPrefHeight(45.0D);
        this.setLayoutY(this.getLayoutY() + 4.0D);
    }

    private void setButtonReleasedStyle() {
        this.setStyle(BUTTON_FREE_STYLE);
        this.setPrefHeight(45.0D);
        this.setLayoutY(this.getLayoutY() - 4.0D);
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
