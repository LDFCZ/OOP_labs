package ru.nsu.ccfit.lopatkin.lab4.utils.GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class InfoLabel extends Label {

    private final static String FONT_PATH = "src/main/resources/PastiRegular.otf";
    public final static String BACKGROUND_IMAGE = "label.png";
    public static final int WIDTH = 230;
    public static final int HEIGHT = 55;

    private static Font DEFAULT_FONT = null;

    static {
        try {
            DEFAULT_FONT = Font.loadFont(new FileInputStream(FONT_PATH), 12);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public InfoLabel() {
        this("");
    }

    public InfoLabel(String text) {

        setPrefWidth(WIDTH);
        setPrefHeight(HEIGHT);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, WIDTH, HEIGHT, false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER);
        //setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        setFont(DEFAULT_FONT);
        setText(text);
    }
}
