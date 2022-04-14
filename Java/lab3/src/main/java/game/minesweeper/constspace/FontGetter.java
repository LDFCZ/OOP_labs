package game.minesweeper.constspace;

import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FontGetter {

    private static volatile FontGetter fontGetter;

    private static Font DEFAULT_FONT;
    private static Font SMALL_FONT;

    private FontGetter() {
        try {
            DEFAULT_FONT = Font.loadFont(new FileInputStream(ConstSpace.FONT_PATH), 23);
            SMALL_FONT = Font.loadFont(new FileInputStream(ConstSpace.FONT_PATH), 14);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static FontGetter getInstance() {
        //Double-check with synchronized block for thread saving
        if (fontGetter == null) {
            synchronized (FontGetter.class) {
                if (fontGetter == null) {
                    fontGetter = new FontGetter();
                }
            }
        }
        return fontGetter;
    }

    public Font getDefaultFont() {
        return DEFAULT_FONT;
    }

    public Font getSmallFont() {
        return SMALL_FONT;
    }

}
