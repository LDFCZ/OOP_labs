package game.minesweeper.views.GUI;

import game.minesweeper.constspace.ConstSpace;
import game.minesweeper.constspace.FontGetter;
import game.minesweeper.controllers.MenuController;
import game.minesweeper.models.GameModel;
import game.minesweeper.models.StatModel;
import game.minesweeper.utils.GUI.*;
import game.minesweeper.utils.Mode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MenuViewGUI extends ViewGUI {

    private final MenuController menuController;
    private final GameModel gameModel;
    private final StatModel statModel = new StatModel();

    private final List<GameButton> buttons;
    private List<ModePicker> modeList;
    private Mode chosenMod;

    private final AnchorPane anchorPane = new AnchorPane();

    private GameSubScene sceneToHide;

    private GameSubScene helpSubScene;
    private GameSubScene homeSubScene;
    private GameSubScene settingsSubScene;
    private GameSubScene statSubScene;
    private GameSubScene playSubScene;

    public MenuViewGUI(Stage stage, GameModel gameModel) {
        super(stage);
        this.gameModel = gameModel;
        this.menuController = new MenuController(gameModel);
        buttons = new ArrayList<>();

        createSubScenes();
        CreateButtons();
        createBackground();

        setNewScene(anchorPane);
        show();

        showSubScene(homeSubScene);
    }

    private void addMenuButtons(GameButton button) {
        button.setLayoutX(ConstSpace.MENU_BUTTON_START_X);
        button.setLayoutY(ConstSpace.MENU_BUTTON_START_Y + buttons.size() * ConstSpace.SHIFT);
        buttons.add(button);
        anchorPane.getChildren().add(button);
    }

    private void CreateButtons() {
        createHomeButton();
        createPlayButton();
        createStatButton();
        createHelpButton();
        createSettingsButton();
        createExitButton();
    }

    private void createSubScenes() {
        helpSubScene = new GameSubScene();
        anchorPane.getChildren().add(helpSubScene);
        GameLabel helpText = new GameLabel(ConstSpace.HELP_TEXT);
        helpText.setPrefSizes(ConstSpace.HELP_TEXT_WIDTH, ConstSpace.HELP_TEXT_HEIGHT);
        helpSubScene.getPane().getChildren().add(helpText);


        settingsSubScene = new GameSubScene();
        anchorPane.getChildren().add(settingsSubScene);
        settingsSubScene.getPane().getChildren().add(createModsToChose());

        statSubScene = new GameSubScene();
        anchorPane.getChildren().add(statSubScene);

        playSubScene = new GameSubScene();
        anchorPane.getChildren().add(playSubScene);
    }

    private void createHomeButton() {
        GameButton homeButton = new GameButton(ConstSpace.HOME);
        addMenuButtons(homeButton);

        homeSubScene = new GameSubScene(ConstSpace.ADD);
        anchorPane.getChildren().add(homeSubScene);

        homeButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showSubScene(homeSubScene);
            }
        });
    }

    private void createPlayButton() {
        GameButton startButton = new GameButton(ConstSpace.PLAY);
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                refreshPlaySubScene();
                showSubScene(playSubScene);
            }
        });
    }

    private void refreshPlaySubScene() {
        playSubScene.getPane().getChildren().clear();

        TextField name = new TextField();
        name.setPrefWidth(ConstSpace.PREF_WIDTH);
        name.setPrefHeight(ConstSpace.PREF_HEIGHT);
        name.setFont(FontGetter.getInstance().getSmallFont());
        name.setPromptText(ConstSpace.PROMPT_TEXT);
        name.setLayoutX(ConstSpace.PLAY_LAYOUT_START_POS);
        name.setLayoutY(ConstSpace.PLAY_LAYOUT_START_POS);
        playSubScene.getPane().getChildren().add(name);

        GameButton submit = new GameButton(ConstSpace.SUBMIT);
        submit.setLayoutX(ConstSpace.PLAY_LAYOUT_MAX_X_POS);
        submit.setLayoutY(ConstSpace.PLAY_LAYOUT_START_POS);
        playSubScene.getPane().getChildren().add(submit);
        submit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (!name.getText().isEmpty()) {
                    if (menuController.setPlayerName(name.getText())) {
                        GameViewGUI new_view = new GameViewGUI(getStage(), gameModel, gameModel.getMode(), gameModel.getPlayerName());
                    }
                    else {
                        InfoLabel label = new InfoLabel(ConstSpace.BAD_NAME);
                        label.setLayoutX(ConstSpace.PLAY_LAYOUT_START_POS);
                        label.setLayoutY(ConstSpace.PLAY_LAYOUT_MAX_Y_POS);
                        playSubScene.getPane().getChildren().add(label);
                    }
                } else {
                    InfoLabel label = new InfoLabel(ConstSpace.NAME_IS_EMPTY);
                    label.setLayoutX(ConstSpace.PLAY_LAYOUT_START_POS);
                    label.setLayoutY(ConstSpace.PLAY_LAYOUT_MAX_Y_POS);
                    playSubScene.getPane().getChildren().add(label);
                }
            }
        });

        GameButton clear = new GameButton(ConstSpace.CLEAR);
        clear.setLayoutX(ConstSpace.PLAY_LAYOUT_MAX_X_POS);
        clear.setLayoutY(ConstSpace.PLAY_LAYOUT_MAX_Y_POS);
        playSubScene.getPane().getChildren().add(clear);
        clear.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e) {
                name.clear();
            }
        });

    }

    private void createStatButton() {
        GameButton startButton = new GameButton(ConstSpace.STATISTIC);
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                refreshStat();
                showSubScene(statSubScene);
            }
        });
    }

    private void refreshStat() {
        statSubScene.getPane().getChildren().clear();
        List<String[]> data = statModel.getStat();
        int i = 0;
        GameLabel topLabel = new GameLabel(ConstSpace.STAT_LABEL);
        topLabel.setLayoutX(ConstSpace.STAT_TOP_LAYOUT_POS);
        topLabel.setLayoutY(ConstSpace.STAT_TOP_LAYOUT_POS);
        statSubScene.getPane().getChildren().add(topLabel);
        for (String[] d : data) {
            if (i == ConstSpace.MAX_TOP_PLAYERS_COUNT) break;
            GameLabel label = new GameLabel(String.valueOf(i + 1) + ConstSpace.BRACKET + d[ConstSpace.NAME] + ConstSpace.SPACE + d[ConstSpace.COUNT]);
            label.setLayoutX(ConstSpace.STAT_TOP_LAYOUT_POS);
            label.setLayoutY(ConstSpace.STAT_Y_LAYOUT_POS + i++ * ConstSpace.SMALL_SHIFT);
            statSubScene.getPane().getChildren().add(label);
        }
    }

    private void createHelpButton() {
        GameButton startButton = new GameButton(ConstSpace.HELP);
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showSubScene(helpSubScene);
            }
        });
    }

    private void createSettingsButton() {
        GameButton startButton = new GameButton(ConstSpace.SETTINGS);
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showSubScene(settingsSubScene);
            }
        });
    }

    private void createExitButton() {
        GameButton startButton = new GameButton(ConstSpace.EXIT);
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                getStage().close();
            }
        });
    }

    private HBox createModsToChose() {
        HBox box = new HBox();
        box.setSpacing(ConstSpace.SPACING);
        box.setPrefWidth(ConstSpace.HELP_TEXT_HEIGHT);
        modeList = new ArrayList<>();
        for (Mode mode : Mode.values()) {
            ModePicker modeToPick = new ModePicker(mode);
            modeList.add(modeToPick);
            box.getChildren().add(modeToPick);
            modeToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (ModePicker mode : modeList) {
                        mode.setIsCircleChosen(false);
                    }
                    modeToPick.setIsCircleChosen(true);
                    chosenMod = modeToPick.getMode();
                    menuController.setFieldParameters(chosenMod);
                }
            });
            if (modeToPick.getMode() == Mode.SMALL && gameModel.getMode() == null) {
                modeToPick.setIsCircleChosen(true);
                chosenMod = modeToPick.getMode();
                menuController.setFieldParameters(chosenMod);
            }
            if (modeToPick.getMode() == gameModel.getMode()) {
                modeToPick.setIsCircleChosen(true);
                chosenMod = modeToPick.getMode();
                menuController.setFieldParameters(chosenMod);
            }
        }
        box.setLayoutX(ConstSpace.STAT_TOP_LAYOUT_POS);
        box.setLayoutY(ConstSpace.PLAY_LAYOUT_START_POS);
        return box;
    }

    private void createBackground() {
        Image backgroundImage = new Image(ConstSpace.BG_IMAGE, 0, 0, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        anchorPane.setBackground(new Background(background));
    }

    private void showSubScene(GameSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;
    }
}
