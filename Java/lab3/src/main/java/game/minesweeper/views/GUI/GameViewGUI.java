package game.minesweeper.views.GUI;

import game.minesweeper.constspace.ConstSpace;
import game.minesweeper.controllers.GameController;
import game.minesweeper.models.GameModel;
import game.minesweeper.utils.GUI.FieldPiece;
import game.minesweeper.utils.GUI.GameButton;
import game.minesweeper.utils.GUI.GameLabel;
import game.minesweeper.utils.GUI.GameSubScene;
import game.minesweeper.utils.Mode;
import game.minesweeper.utils.MoveResults;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Vector;

public class GameViewGUI extends ViewGUI{

    public static final String BACK = "BACK";
    public static final int BACK_BUTTON_LAYOUT_X = 400;
    public static final int BACK_BUTTON_LAYOUT_Y = 718;
    public static final int LAYOUT_X_START = 162;
    public static final int CELL_SIZE = 41;
    public static final int LAYOUT_Y_START = 15;
    public static final String WIN = "WIN";
    public static final String OK = "OK";
    public static final int END_GAME_X = 224;
    public static final int END_GAME_Y = 100;
    public static final int LABEL_X = 270;
    public static final int LABEL_Y = 250;
    public static final int BACK_X = 215;
    public static final int BACK_Y = 300;
    public static final String MINES = "Mines: ";

    private final GameModel gameModel;
    private final GameController gameController;

    private final AnchorPane anchorPane = new AnchorPane();

    private GameLabel mineCounter;

    private Vector<Vector<FieldPiece>> field;

    public GameViewGUI(Stage stage, GameModel gameModel, Mode mode, String playerName) {
        super(stage);
        this.gameModel = gameModel;
        this.gameController = new GameController(gameModel, mode, playerName);


        createBackground();
        createBackButton();
        createField();
        setNewScene(anchorPane);
        show();
    }

    private void createBackButton() {
        GameButton backButton = new GameButton(BACK);
        backButton.setLayoutX(BACK_BUTTON_LAYOUT_X);
        backButton.setLayoutY(BACK_BUTTON_LAYOUT_Y);
        anchorPane.getChildren().add(backButton);
        createMineCounter();
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                MenuViewGUI new_view = new MenuViewGUI(getStage(), gameModel);
            }
        });
    }

    private void createField() {
        double size = gameModel.getFieldSize();
        field = new Vector<>();
        for (int i = 0; i < gameModel.getFieldSize(); i++) {
            field.add(new Vector<>());
            for (int j = 0; j < gameModel.getFieldSize(); j++) {
                FieldPiece fp = new FieldPiece(LAYOUT_X_START + CELL_SIZE * i, LAYOUT_Y_START + CELL_SIZE * j, i, j, CELL_SIZE);
                int finalI = i;
                int finalJ = j;
                fp.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        MouseButton button = event.getButton();
                        if(button==MouseButton.PRIMARY){
                            processMove(gameController.openCell(finalI, finalJ));
                        }else if(button==MouseButton.SECONDARY) {
                            processMove(gameController.setFlag(finalI, finalJ));
                        }
                        updateFieldCondition();
                    }
                });
                field.get(i).add(fp);
                anchorPane.getChildren().add(fp);
            }
        }
    }

    private void processMove(MoveResults result) {
        if (result == MoveResults.WIN) {
            gameController.writeStat();
            endGame(WIN);
        } else if (result == MoveResults.LOSE) {
            gameController.endGame();
        }
    }

    private void endGame(String status) {
        anchorPane.getChildren().clear();
        GameSubScene endScene = new GameSubScene();

        endScene.setLayoutX(END_GAME_X);
        endScene.setLayoutY(END_GAME_Y);

        GameLabel label = new GameLabel(status);
        label.setLayoutX(LABEL_X);
        label.setLayoutY(LABEL_Y);

        GameButton back = new GameButton(OK);
        back.setLayoutX(BACK_X);
        back.setLayoutY(BACK_Y);

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MenuViewGUI view = new MenuViewGUI(getStage(), gameModel);
            }
        });

        endScene.getPane().getChildren().addAll(label, back);
        anchorPane.getChildren().add(endScene);
    }

    private void updateFieldCondition() {
        for (int i = 0; i < gameModel.getFieldSize(); i++) {
            for (int j = 0; j < gameModel.getFieldSize(); j++) {
                field.get(i).get(j).setCondition(gameModel.getCellCondition(i,j));
            }
        }
    }

    private void createMineCounter() {
        mineCounter = new GameLabel(MINES + gameModel.getMineCount());
        mineCounter.setLayoutX(600);
        mineCounter.setLayoutY(730);
        anchorPane.getChildren().add(mineCounter);
    }

    private void createBackground() {
        Image backgroundImage = new Image(ConstSpace.BG_IMAGE, 0, 0, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        anchorPane.setBackground(new Background(background));
    }
}
