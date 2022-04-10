package ru.nsu.ccfit.lopatkin.lab4.GUI.views;

import javafx.application.Platform;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ru.nsu.ccfit.lopatkin.lab4.GUI.controller.FactoryViewController;
import ru.nsu.ccfit.lopatkin.lab4.GUI.utils.BlockSlider;
import ru.nsu.ccfit.lopatkin.lab4.GUI.utils.BlockSubScene;
import ru.nsu.ccfit.lopatkin.lab4.GUI.utils.InfoLabel;
import ru.nsu.ccfit.lopatkin.lab4.GUI.utils.ScrollSubScene;

import ru.nsu.ccfit.lopatkin.lab4.factory.FactoryCreator;


import java.util.*;

public class FactoryView {

    private final AnchorPane anchorPane = new AnchorPane();
    private final Stage stage;

    private final FactoryCreator factoryCreator;


    private final FactoryViewController factoryViewController;

    public FactoryView (Stage stage, FactoryCreator factoryCreator) {
        this.stage = stage;
        this.factoryCreator = factoryCreator;
        this.factoryViewController = new FactoryViewController(factoryCreator);
        this.stage.setScene(new Scene(anchorPane, 1635, 900));

        createBlockSubScenes();
        createScrollSubScenes();
        createBackground();
        this.stage.show();
    }

    private void createScrollSubScenes() {
        ScrollSubScene workersSubScene = new ScrollSubScene(648, 162);
        createWorkers(workersSubScene);

        BlockSlider workersDelay = new BlockSlider(0,6000);
        workersDelay.setLayoutX(700);
        workersDelay.setLayoutY(125);
        workersDelay.valueProperty().addListener((observableValue, old_val, new_val) -> factoryViewController.changeBuildTaskDelay(new_val.intValue()));

        ScrollSubScene accessoriesSuppliersSubScene = new ScrollSubScene(648, 495);
        createAccessoriesSuppliers(accessoriesSuppliersSubScene);

        BlockSlider accessoriesDelay = new BlockSlider(0,6000);
        accessoriesDelay.setLayoutX(700);
        accessoriesDelay.setLayoutY(455);
        accessoriesDelay.valueProperty().addListener((observableValue, old_val, new_val) -> factoryViewController.changeSupplyAccessoriesDelay(new_val.intValue()));

        ScrollSubScene dealersSubScene = new ScrollSubScene(1097, 400);
        createDealers(dealersSubScene);

        BlockSlider dealersDelay = new BlockSlider(0,6000);
        dealersDelay.setLayoutX(1150);
        dealersDelay.setLayoutY(360);
        dealersDelay.valueProperty().addListener((observableValue, old_val, new_val) -> factoryViewController.changeSellCarDelay(new_val.intValue()));

        anchorPane.getChildren().addAll(workersSubScene, accessoriesSuppliersSubScene, dealersSubScene, workersDelay, accessoriesDelay, dealersDelay);
    }

    private void createDealers(ScrollSubScene dealersSubScene) {
        final AnchorPane container = new AnchorPane();
        for (int i = 0; i < factoryCreator.getDealerCount(); i++) {
            BlockSubScene bs = new BlockSubScene("Dealer " + String.valueOf(i + 1), 50, i * 105);
            container.getChildren().add(bs);
        }
        dealersSubScene.getPane().setContent(container);

    }

    private void createWorkers(ScrollSubScene workersSubScene) {
        final AnchorPane container = new AnchorPane();
        for (int i = 0; i < factoryCreator.getWorkerCount(); i++) {
            container.getChildren().add(new BlockSubScene("Worker " + String.valueOf(i + 1), 50, i * 105));
        }
        workersSubScene.getPane().setContent(container);
    }

    private void createAccessoriesSuppliers(ScrollSubScene accessoriesSuppliersSubScene) {
        final AnchorPane container = new AnchorPane();
        for (int i = 0; i < factoryCreator.getAccessoriesSupplierCount(); i++) {
            container.getChildren().add(new BlockSubScene("Accessory Supplier " + String.valueOf(i + 1), 50, i * 105));
        }
        accessoriesSuppliersSubScene.getPane().setContent(container);
    }

    private void createBlockSubScenes() {
        BlockSubScene bodySupplier = new BlockSubScene("body supplier",150, 17, 0, 6000);
        bodySupplier.addSliderListener((observableValue, old_val, new_val) -> factoryViewController.changeSupplyCarBodyDelay(new_val.intValue()));

        BlockSubScene bodyStore = new BlockSubScene("body store", 292, 336);

        BlockSubScene engineSupplier = new BlockSubScene("engine supplier", 574, 17, 0, 6000);
        engineSupplier.addSliderListener((observableValue, old_val, new_val) -> factoryViewController.changeSupplyEnginDelay(new_val.intValue()));

        BlockSubScene engineStore = new BlockSubScene("engine store", 290, 169);

        BlockSubScene accessoriesStore = new BlockSubScene("accessories store", 290, 509);

        BlockSubScene carsStore = new BlockSubScene("ready car store",1233, 193);

        InfoLabel carsStoreController = new InfoLabel("car store controller");
        carsStoreController.setLayoutX(1233);
        carsStoreController.setLayoutY(60);

        InfoLabel carsSold = new InfoLabel("cars sold: ");
        carsSold.setLayoutX(684);
        carsSold.setLayoutY(810);

        anchorPane.getChildren().addAll(bodySupplier, bodyStore, engineSupplier, engineStore, accessoriesStore, carsStore, carsStoreController, carsSold);

        Timer upd = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    carsStore.setProgress(factoryCreator.getCarStorage().getOccupancy());
                    bodyStore.setProgress(factoryCreator.getCarBodyStorage().getOccupancy());
                    accessoriesStore.setProgress(factoryCreator.getAccessoriesStorage().getOccupancy());
                    engineStore.setProgress(factoryCreator.getEngineStorage().getOccupancy());
                });
            }
        };
        upd.schedule(task, 0, 300);

    }

    private void createBackground() {
        Image backgroundImage = new Image("bg.png", 0, 0, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        anchorPane.setBackground(new Background(background));
    }
}

