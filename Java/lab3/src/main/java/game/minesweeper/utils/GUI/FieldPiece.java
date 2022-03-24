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

    private Boolean isFlag = false;
    private Boolean isOpen = false;

    private static final String BUTTON_CLOSE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('field_piece_1.png');";
    private static final String BUTTON_FLAG_STYLE = "-fx-background-color: transparent; -fx-background-image: url('field_piece_flag.png');";
    private static final String BUTTON_OPEN_STYLE = "-fx-background-color: transparent; -fx-background-image: url('field_piece_2.png');";

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

    public void setFlag() {
        if (!isFlag && !isOpen) {
            this.setStyle(BUTTON_FLAG_STYLE);
            isFlag = true;
        } else if(isOpen) {
            this.setStyle(BUTTON_OPEN_STYLE);
        } else {
            this.setStyle(BUTTON_CLOSE_STYLE);
            isFlag = false;
        }
    }

    public void setOpen(int mineCount) {
        if (!isFlag) {
            if (mineCount != 0)
                this.setText(String.valueOf(mineCount));
            this.setStyle(BUTTON_OPEN_STYLE);
            isOpen = true;
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
