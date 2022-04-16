package ru.nsu.ccfit.lopatkin.lab4.GUI.utils;

import javafx.scene.control.Slider;

public final class BlockSlider extends Slider {

    private static final int BLOCK_INCREMENT = 100;
    private static final int MAJOR_TICK_UNIT = 1000;
    private static final int MINOR_TICK_COUNT = 9;

    public BlockSlider() {
        setShowTickMarks(true);
        setShowTickLabels(true);
        setBlockIncrement(BLOCK_INCREMENT);
        setMajorTickUnit(MAJOR_TICK_UNIT);
        setMinorTickCount(MINOR_TICK_COUNT);
        setSnapToTicks(true);

        setPrefSize(230, 25);
    }

    public BlockSlider(int min, int max) {
        this();
        setMin(min);
        setMax(max);
    }
}
