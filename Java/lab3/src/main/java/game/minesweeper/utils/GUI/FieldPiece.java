package game.minesweeper.utils.GUI;

import game.minesweeper.constspace.ConstSpace;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;

public class FieldPiece extends Button {

    private final int x;
    private final int y;

    private static final String BUTTON_CLOSE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('field_piece_1.png');";
    private static final String BUTTON_FLAG_STYLE = "-fx-background-color: transparent; -fx-background-image: url('field_piece_flag.png');";
    private static final String BUTTON_OPEN_STYLE = "-fx-background-color: transparent; -fx-background-image: url('field_piece_2.png');";
    private static final String BUTTON_MINE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('mine.png');";

    public FieldPiece(double posX, double posY, int x, int y, double size) {

        this.x = x;
        this.y = y;

        this.setFont(ConstSpace.getDefaultFont());
        this.setPrefWidth(size);
        this.setPrefHeight(size);
        this.setLayoutX(posX);
        this.setLayoutY(posY);
        this.setStyle(BUTTON_CLOSE_STYLE);
        initializeButtonListeners();
    }

    public void setCondition(String condition) {
        if (condition.equals("F")) {
            this.setStyle(BUTTON_FLAG_STYLE);
        } else if (condition.equals("*")) {
            this.setStyle(BUTTON_MINE_STYLE);
        } else if (condition.equals("0")) {
            this.setStyle(BUTTON_OPEN_STYLE);
        } else if (condition.equals("#")){
            this.setStyle(BUTTON_CLOSE_STYLE);
        } else {
            this.setText(String.valueOf(condition));
            this.setStyle(BUTTON_OPEN_STYLE);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void initializeButtonListeners() {
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FieldPiece.this.setEffect(new DropShadow());
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FieldPiece.this.setEffect((Effect)null);
            }
        });
    }
}
