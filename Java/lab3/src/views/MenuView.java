package views;

import constspace.CONSTSPACE;
import controllers.MenuController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import utils.GameButton;
import utils.GameSubScene;


import java.util.ArrayList;
import java.util.List;

public class MenuView {
    private final Stage stage;
    private final AnchorPane pane;

    private final List<GameButton> buttons;
    private final MenuController menuController;

    private final static int MENU_BUTTON_START_X = 100;
    private final static int MENU_BUTTON_START_Y = 100;

    private GameSubScene sceneToHide;

    private GameSubScene helpSubscene;
    private GameSubScene homeSubscene;


    public MenuView (Stage stage) {
        buttons = new ArrayList<>();
        this.pane = new AnchorPane();
        this.stage = stage;
        stage.setScene(new Scene(pane, CONSTSPACE.WIDTH, CONSTSPACE.HEIGHT));
        menuController = new MenuController();

        createSubScenes();
        CreateButtons();
        createBackground();
        showSubScene(homeSubscene);
        stage.show();
    }

    private void addMenuButtons(GameButton button) {
        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + buttons.size() * 100);
        buttons.add(button);
        pane.getChildren().add(button);
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

        helpSubscene = new GameSubScene();
        pane.getChildren().add(helpSubscene);
        Label helpText = new Label("Help is here!");

        helpText.setPrefWidth(380);
        helpText.setPrefHeight(49);
        helpText.setWrapText(true);
        helpText.setAlignment(Pos.CENTER);
        helpSubscene.getPane().getChildren().add(helpText);


        homeSubscene = new GameSubScene("/resources/add.png");
        pane.getChildren().add(homeSubscene);


    }

    private void showSubScene(GameSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;
    }


    private void createHomeButton() {
        GameButton startButton = new GameButton("HOME");
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showSubScene(homeSubscene);
            }
        });
    }

    private void createPlayButton() {
        GameButton startButton = new GameButton("PLAY");
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                menuController.doSmth();
            }
        });
    }

    private void createStatButton() {
        GameButton startButton = new GameButton("STATISTIC");
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                StatView sv = new StatView(stage);
            }
        });
    }

    private void createHelpButton() {
        GameButton startButton = new GameButton("HELP");
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                showSubScene(helpSubscene);
            }
        });
    }

    private void createSettingsButton() {
        GameButton startButton = new GameButton("SETTINGS");
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.print("!");
            }
        });
    }

    private void createExitButton() {
        GameButton startButton = new GameButton("EXIT");
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
    }

    private void createBackground() {
        Image backgroundImage = new Image("/resources/bg.jpg", 0, 0, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        pane.setBackground(new Background(background));
    }
}
