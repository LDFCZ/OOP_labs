package ru.nsu.ccfit.lopatkin.lab4.utils.GUI;

import javafx.scene.SubScene;
import javafx.scene.control.ScrollPane;

public final class ScrollSubScene extends SubScene {

    public ScrollSubScene(int x, int y) {
        super(new ScrollPane(), 345, 280);
        getPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setLayoutX(x);
        setLayoutY(y);
    }

    public ScrollPane getPane() {
        return (ScrollPane) this.getRoot();
    }
}
