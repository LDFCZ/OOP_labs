package game.minesweeper.views.GUI;

import game.minesweeper.constspace.ConstSpace;
import game.minesweeper.controllers.GameController;
import game.minesweeper.models.GameModel;
import game.minesweeper.utils.GUI.FieldPiece;
import game.minesweeper.utils.GUI.GameButton;
import game.minesweeper.utils.GUI.GameLabel;
import game.minesweeper.utils.Mode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameViewGUI extends ViewGUI{
    private final GameModel gameModel;
    private final GameController gameController;

    private final AnchorPane anchorPane = new AnchorPane();

    private GameLabel mineCounter;

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
        GameButton backButton = new GameButton("BACK");
        backButton.setLayoutX(400);
        backButton.setLayoutY(718);
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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                FieldPiece fp = new FieldPiece(162 + 700/size * i, 15 + 700/size*j, i, j, 700/size - 1);
                int finalI = i;
                int finalJ = j;
                fp.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        MouseButton button = event.getButton();
                        if(button==MouseButton.PRIMARY){
                            System.out.print(finalI);
                            System.out.print(finalJ);
                            System.out.print(" ");
                            fp.setOpen(0);
                            // TODO сделать открытие клеток......... и начало игры соответсвенно
                        }else if(button==MouseButton.SECONDARY) {
                            fp.setFlag();
                        }
                    }
                });
                
                anchorPane.getChildren().add(fp);
            }
        }
    }

    private void createMineCounter() {
        mineCounter = new GameLabel("Mines: " + gameModel.getMineCount());
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
