package game.minesweeper.utils.GUI;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class GameSubScene extends SubScene {

    private final static String DEFAULT_BACKGROUND_IMAGE = "panel.png";
    public static final int LAYOUT_X = 1024;
    public static final int LAYOUT_Y = 90;
    public static final double DURATION = 0.3;
    public static final int VISIBLE_POS_X = -676;
    public static final int HIDDEN_POS_X = 0;

    private boolean isHidden;


    private final static int SCENE_WIDTH = 600;
    private final static int SCENE_HEIGHT = 560;

    public GameSubScene() {
        super(new AnchorPane(), SCENE_WIDTH, SCENE_HEIGHT);

        prefWidth(SCENE_WIDTH);
        prefHeight(SCENE_HEIGHT);

       BackgroundImage image = new BackgroundImage(new Image(DEFAULT_BACKGROUND_IMAGE, SCENE_WIDTH, SCENE_HEIGHT, false, true),
               BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));

        isHidden = true;

        setLayoutX(LAYOUT_X);
        setLayoutY(LAYOUT_Y);
    }

    public GameSubScene(String imagePath) {
        super(new AnchorPane(), SCENE_WIDTH, SCENE_HEIGHT);

        prefWidth(SCENE_WIDTH);
        prefHeight(SCENE_HEIGHT);

        BackgroundImage image = new BackgroundImage(new Image(imagePath, SCENE_WIDTH, SCENE_HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));

        isHidden = true;

        setLayoutX(LAYOUT_X);
        setLayoutY(LAYOUT_Y);
    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(DURATION));
        transition.setNode(this);

        if (isHidden) {
            transition.setToX(VISIBLE_POS_X);
            isHidden = false;
        } else {

            transition.setToX(HIDDEN_POS_X);
            isHidden = true;
        }
        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}
