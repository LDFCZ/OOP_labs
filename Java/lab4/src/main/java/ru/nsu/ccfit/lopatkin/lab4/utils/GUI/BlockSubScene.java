package ru.nsu.ccfit.lopatkin.lab4.utils.GUI;

import javafx.beans.value.ChangeListener;
import javafx.scene.SubScene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

public final class BlockSubScene extends SubScene {

    private final Slider slider = new Slider();
    private final InfoLabel label = new InfoLabel();
    private final ProgressBar bar = new ProgressBar();

    public BlockSubScene(String name, double x, double y) {
        super(new AnchorPane(), 240, 105);

        label.setText(name);
        label.setLayoutX(5);
        label.setLayoutY(45);
        this.getPane().getChildren().add(label);

        bar.setPrefSize(230,5);
        bar.setLayoutX(5);
        bar.setLayoutY(38);
        this.getPane().getChildren().add(bar);

        setLayoutX(x);
        setLayoutY(y);
    }

    public BlockSubScene(String name,double x, double y, int min_value, int max_value) {
        this(name, x,y);
        slider.setMin(min_value);
        slider.setMax(max_value);
        slider.setValue((max_value + min_value) / 2);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setBlockIncrement(100);
        slider.setMajorTickUnit(1000);
        slider.setMinorTickCount(9);
        slider.setSnapToTicks(true);

        slider.setPrefSize(230, 25);
        slider.setLayoutX(5);
        slider.setLayoutY(5);

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
