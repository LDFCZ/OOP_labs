package game.minesweeper.utils.GUI;

import game.minesweeper.constspace.FontGetter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;


public class InfoLabel extends Label{

    public final static String BACKGROUND_IMAGE = "red_info_label.png";
    public static final int WIDTH = 190;
    public static final int HEIGHT = 50;
    public static final int PADDING = 10;


    public InfoLabel(String text) {

        setPrefWidth(WIDTH);
        setPrefHeight(HEIGHT);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, WIDTH, HEIGHT, false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        setFont(FontGetter.getInstance().getSmallFont());
        setText(text);
    }
}
