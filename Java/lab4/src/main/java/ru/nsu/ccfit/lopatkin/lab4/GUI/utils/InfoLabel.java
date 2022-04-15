package ru.nsu.ccfit.lopatkin.lab4.GUI.utils;

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

    public final static String BACKGROUND_IMAGE = "label.png";
    public static final int WIDTH = 230;
    public static final int HEIGHT = 55;

    public InfoLabel(String text) {

        setPrefWidth(WIDTH);
        setPrefHeight(HEIGHT);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, WIDTH, HEIGHT, false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER);
        setFont(FontFactory.getInstance().getDefaultFont());
        setText(text);
    }
}
