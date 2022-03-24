package game.minesweeper.utils.GUI;

import game.minesweeper.constspace.ConstSpace;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class GameLabel extends Label {

    public GameLabel(String text) {
        setText(text);
        setFont(ConstSpace.getDefaultFont());
        setWrapText(true);
        setAlignment(Pos.CENTER);
    }

    public void setPrefSizes(double width, double height) {
        setPrefWidth(width);
        setPrefHeight(height);
    }
}
