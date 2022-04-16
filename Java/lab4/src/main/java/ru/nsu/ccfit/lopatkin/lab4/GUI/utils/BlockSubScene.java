package ru.nsu.ccfit.lopatkin.lab4.GUI.utils;

import javafx.beans.value.ChangeListener;
import javafx.scene.SubScene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

public final class BlockSubScene extends SubScene {

    private static final int WIDTH = 240;
    private static final int HEIGHT = 105;
    private static final int LAYOUT_X = 5;
    private static final int LABEL_LAYOUT_Y = 45;
    private static final int BAR_LAYOUT_Y = 38;
    private static final int PREF_WIDTH = 230;
    private static final int PREF_HEIGHT = 15;

    private final BlockSlider slider = new BlockSlider();
    private final InfoLabel label = new InfoLabel();
    private final ProgressBar bar = new ProgressBar();

    public BlockSubScene(String name, double x, double y) {
        super(new AnchorPane(), WIDTH, HEIGHT);

        label.setText(name);
        label.setLayoutX(LAYOUT_X);
        label.setLayoutY(LABEL_LAYOUT_Y);
        this.getPane().getChildren().add(label);

        bar.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
        bar.setLayoutX(LAYOUT_X);
        bar.setLayoutY(BAR_LAYOUT_Y);
        bar.setProgress(0);
        this.getPane().getChildren().add(bar);

        setLayoutX(x);
        setLayoutY(y);
    }

    public BlockSubScene(String name,double x, double y, int min_value, int max_value) {
        this(name, x,y);
        slider.setMin(min_value);
        slider.setMax(max_value);
        slider.setValue((max_value + min_value) / 2);

        slider.setLayoutX(LAYOUT_X);
        slider.setLayoutY(LAYOUT_X);

        this.getPane().getChildren().add(slider);

    }

    public void addSliderListener(ChangeListener<Number> changeListener) {
        slider.valueProperty().addListener(changeListener);
    }

    public void setProgress(double val) {
        bar.setProgress(val);
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}
