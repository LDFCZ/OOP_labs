package ru.nsu.ccfit.lopatkin.lab4.GUI.utils;

import javafx.scene.control.Slider;

public final class BlockSlider extends Slider {

    public BlockSlider(int min, int max) {
        setMin(min);
        setMax(max);
        setValue((min + max) / 2);
        setShowTickMarks(true);
        setShowTickLabels(true);
        setBlockIncrement(100);
        setMajorTickUnit(1000);
        setMinorTickCount(9);
        setSnapToTicks(true);

        setPrefSize(230, 25);
    }
}
