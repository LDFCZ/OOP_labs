package ru.nsu.ccfit.lopatkin.lab4.utils.GUI;

import javafx.scene.SubScene;
import javafx.scene.control.ScrollPane;

public class ScrollSubScene extends SubScene {

    public ScrollSubScene(int x, int y) {
        super(new ScrollPane(), 345, 280);

        setLayoutX(x);
        setLayoutY(y);
    }

    public ScrollPane getPane() {
        return (ScrollPane) this.getRoot();
    }
}
