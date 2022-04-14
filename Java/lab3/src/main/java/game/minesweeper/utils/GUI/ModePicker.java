package game.minesweeper.utils.GUI;

import game.minesweeper.utils.Mode;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ModePicker extends VBox {
    public static final String X = "x";
    public static final String MINES = " mines: ";
    public static final int SPACING = 20;

    private final ImageView circleImage;

    private static final String NOT_CHOSEN = "grey_circle.png";
    private static final String CHOSEN = "red_chosen.png";

    private final Mode mode;

    private boolean isCircleChosen;


    public ModePicker(Mode mode) {
        circleImage = new ImageView(NOT_CHOSEN);
        GameLabel label = new GameLabel(mode.getFieldSize().toString() + X + mode.getFieldSize().toString() + MINES + mode.getMineCount().toString());
        this.mode = mode;
        isCircleChosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(SPACING);
        this.getChildren().add(circleImage);
        this.getChildren().add(label);

    }

    public Mode getMode() {
        return mode;
    }

    public boolean getCircleChosen() {
        return isCircleChosen;
    }

    public void setIsCircleChosen(boolean isCircleChosen) {
        this.isCircleChosen = isCircleChosen;
        String imageToSet = this.isCircleChosen ? CHOSEN : NOT_CHOSEN;
        circleImage.setImage(new Image(imageToSet));
    }
}
