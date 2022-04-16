package ru.nsu.ccfit.lopatkin.lab4.GUI.utils;

import javafx.scene.SubScene;
import javafx.scene.control.ScrollPane;

public final class ScrollSubScene extends SubScene {

    private static final int WIDTH = 345;

    private static final int HEIGHT = 280;

    public ScrollSubScene(int x, int y) {
        super(new ScrollPane(), WIDTH, HEIGHT);
        getPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setLayoutX(x);
        setLayoutY(y);
    }

    public ScrollPane getPane() {
        return (ScrollPane) this.getRoot();
    }
}
