package ru.nsu.ccfit.lopatkin.lab4.GUI.utils;

import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FontFactory {
    private final static String FONT_PATH = "src/main/resources/PastiRegular.otf";
    private static final int SIZE = 12;
    private static Font defaultFont = null;
    private static volatile FontFactory fontFactory;

    private FontFactory() {
        try {
            defaultFont = Font.loadFont(new FileInputStream(FONT_PATH), SIZE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static FontFactory getInstance() {
        if (fontFactory == null) {
            synchronized (FontFactory.class) {
                if (fontFactory == null) {
                    fontFactory = new FontFactory();
                }
            }
        }
        return fontFactory;
    }

    public Font getDefaultFont() {
        return defaultFont;
    }
}
