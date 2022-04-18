package ru.nsu.ccfit.lopatkin.lab4.GUI.views;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ru.nsu.ccfit.lopatkin.lab4.GUI.controller.FactoryViewController;
import ru.nsu.ccfit.lopatkin.lab4.GUI.utils.BlockSlider;
import ru.nsu.ccfit.lopatkin.lab4.GUI.utils.BlockSubScene;
import ru.nsu.ccfit.lopatkin.lab4.GUI.utils.InfoLabel;
import ru.nsu.ccfit.lopatkin.lab4.GUI.utils.ScrollSubScene;
import ru.nsu.ccfit.lopatkin.lab4.factory.FactoryController;

import java.util.Timer;
import java.util.TimerTask;

public class FactoryView {

    private static final int SCENE_WIDTH = 1635;
    private static final int SCENE_HEIGHT = 900;
    private static final int WORKERS_SS_X = 648;
    private static final int WORKERS_SS_Y = 162;
    private static final int WORKERS_MIN = 0;
    private static final int WORKERS_MAX = 6000;
    private static final int WORKERS_S_X = 700;
    private static final int WORKERS_S_Y = 125;
    private static final int ACC_SS_X = 648;
    private static final int ACC_SS_Y = 495;
    private static final int ACC_MIN = 0;
    private static final int ACC_MAX = 6000;
    private static final int ACC_S_X = 700;
    private static final int ACC_S_Y = 455;
    private static final int DEAL_SS_X = 1097;
    private static final int DEAL_SS_Y = 400;
    private static final int DEAL_MIM = 0;
    private static final int DEAL_MAX = 6000;
    private static final int DEAL_S_X = 1150;
    private static final int DEAL_S_Y = 360;
    private static final int SHIFT = 1;
    private static final int BS_X = 50;
    private static final int BS_Y_SHIFT = 105;
    private static final String DEALER = "Dealer ";
    private static final String WORKER = "Worker ";
    private static final String ACCESSORY_SUPPLIER = "Accessory Supplier ";
    private static final String BODY_SUPPLIER = "body supplier";
    private static final String BODY_STORE = "body store";
    private static final String ENGINE_SUPPLIER = "engine supplier";
    private static final String ENGINE_STORE = "engine store";
    private static final String ACCESSORIES_STORE = "accessories store";
    private static final String READY_CAR_STORE = "ready car store";
    private static final String CAR_STORE_CONTROLLER = "car store controller";
    private static final String CARS_SOLD = "cars sold: ";
    private static final String PLUS = "+";
    private static final String BG = "bg.png";
    private static final int BUTTON_X = 1030;
    private static final int BUTTON_Y = 400;
    private static final int PERIOD = 300;
    private static final int BODY_S_X = 150;
    private static final int BODY_S_Y = 17;
    private static final int BODY_S_MIN = 0;
    private static final int BODY_S_MAX = 6000;
    private static final int BODY_ST_X = 292;
    private static final int BODY_ST_Y = 336;
    private static final int ENG_S_X = 574;
    private static final int ENG_S_Y = 17;
    private static final int ENG_S_MIN = 0;
    private static final int ENG_S_MAX = 6000;
    private static final int ENG_ST_X = 290;
    private static final int ENG_ST_Y = 169;
    private static final int ACC_ST_X = 290;
    private static final int ACC_ST_Y = 509;
    private static final int CAR_ST_X = 1233;
    private static final int CAR_ST_Y = 193;
    private static final int CAR_ST_C_X = 1233;
    private static final int CAR_ST_C_Y = 60;
    private static final int CAR_SOLD_L_X = 684;
    private static final int CAR_SOLD_L_Y = 810;


    private final AnchorPane anchorPane = new AnchorPane();
    private final Stage stage;

    private final FactoryController factoryController;


    private final FactoryViewController factoryViewController;

    public FactoryView (Stage stage, FactoryController factoryController) {
        this.stage = stage;
        this.factoryController = factoryController;
        this.factoryViewController = new FactoryViewController(factoryController);
        this.stage.setScene(new Scene(anchorPane, SCENE_WIDTH, SCENE_HEIGHT));

        createBlockSubScenes();
        createScrollSubScenes();
        createBackground();
        addSellCarButton();
        this.stage.show();
    }

    private void createScrollSubScenes() {
        ScrollSubScene workersSubScene = new ScrollSubScene(WORKERS_SS_X, WORKERS_SS_Y);
        createWorkers(workersSubScene);

        BlockSlider workersDelay = new BlockSlider(WORKERS_MIN, WORKERS_MAX);
        workersDelay.setLayoutX(WORKERS_S_X);
        workersDelay.setLayoutY(WORKERS_S_Y);
        workersDelay.valueProperty().addListener((observableValue, old_val, new_val) -> factoryViewController.changeBuildTaskDelay(new_val.intValue()));

        ScrollSubScene accessoriesSuppliersSubScene = new ScrollSubScene(ACC_SS_X, ACC_SS_Y);
        createAccessoriesSuppliers(accessoriesSuppliersSubScene);

        BlockSlider accessoriesDelay = new BlockSlider(ACC_MIN, ACC_MAX);
        accessoriesDelay.setLayoutX(ACC_S_X);
        accessoriesDelay.setLayoutY(ACC_S_Y);
        accessoriesDelay.valueProperty().addListener((observableValue, old_val, new_val) -> factoryViewController.changeSupplyAccessoriesDelay(new_val.intValue()));

        ScrollSubScene dealersSubScene = new ScrollSubScene(DEAL_SS_X, DEAL_SS_Y);
        createDealers(dealersSubScene);

        BlockSlider dealersDelay = new BlockSlider(DEAL_MIM, DEAL_MAX);
        dealersDelay.setLayoutX(DEAL_S_X);
        dealersDelay.setLayoutY(DEAL_S_Y);
        dealersDelay.valueProperty().addListener((observableValue, old_val, new_val) -> factoryViewController.changeSellCarDelay(new_val.intValue()));

        anchorPane.getChildren().addAll(workersSubScene, accessoriesSuppliersSubScene, dealersSubScene, workersDelay, accessoriesDelay, dealersDelay);
    }

    private void createDealers(ScrollSubScene dealersSubScene) {
        final AnchorPane container = new AnchorPane();
        for (int i = 0; i < factoryController.getDealerCount(); i++) {
            BlockSubScene bs = new BlockSubScene(DEALER + String.valueOf(i + SHIFT), BS_X, i * BS_Y_SHIFT);
            container.getChildren().add(bs);
        }
        dealersSubScene.getPane().setContent(container);

    }

    private void createWorkers(ScrollSubScene workersSubScene) {
        final AnchorPane container = new AnchorPane();
        for (int i = 0; i < factoryController.getWorkerCount(); i++) {
            container.getChildren().add(new BlockSubScene(WORKER + String.valueOf(i + SHIFT), BS_X, i * BS_Y_SHIFT));
        }
        workersSubScene.getPane().setContent(container);
    }

    private void createAccessoriesSuppliers(ScrollSubScene accessoriesSuppliersSubScene) {
        final AnchorPane container = new AnchorPane();
        for (int i = 0; i < factoryController.getAccessoriesSupplierCount(); i++) {
            container.getChildren().add(new BlockSubScene(ACCESSORY_SUPPLIER + String.valueOf(i + SHIFT), BS_X, i * BS_Y_SHIFT));
        }
        accessoriesSuppliersSubScene.getPane().setContent(container);
    }

    private void createBlockSubScenes() {
        BlockSubScene bodySupplier = new BlockSubScene(BODY_SUPPLIER, BODY_S_X, BODY_S_Y, BODY_S_MIN, BODY_S_MAX);
        bodySupplier.addSliderListener((observableValue, old_val, new_val) -> factoryViewController.changeSupplyCarBodyDelay(new_val.intValue()));

        BlockSubScene bodyStore = new BlockSubScene(BODY_STORE, BODY_ST_X, BODY_ST_Y);

        BlockSubScene engineSupplier = new BlockSubScene(ENGINE_SUPPLIER, ENG_S_X, ENG_S_Y, ENG_S_MIN, ENG_S_MAX);
        engineSupplier.addSliderListener((observableValue, old_val, new_val) -> factoryViewController.changeSupplyEnginDelay(new_val.intValue()));

        BlockSubScene engineStore = new BlockSubScene(ENGINE_STORE, ENG_ST_X, ENG_ST_Y);

        BlockSubScene accessoriesStore = new BlockSubScene(ACCESSORIES_STORE, ACC_ST_X, ACC_ST_Y);

        BlockSubScene carsStore = new BlockSubScene(READY_CAR_STORE, CAR_ST_X, CAR_ST_Y);

        InfoLabel carsStoreController = new InfoLabel(CAR_STORE_CONTROLLER);
        carsStoreController.setLayoutX(CAR_ST_C_X);
        carsStoreController.setLayoutY(CAR_ST_C_Y);

        InfoLabel carsSold = new InfoLabel(CARS_SOLD);
        carsSold.setLayoutX(CAR_SOLD_L_X);
        carsSold.setLayoutY(CAR_SOLD_L_Y);

        anchorPane.getChildren().addAll(bodySupplier, bodyStore, engineSupplier, engineStore, accessoriesStore, carsStore, carsStoreController, carsSold);

        Timer upd = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    carsSold.setText(CARS_SOLD + factoryController.getSoldCarCounter());
                    carsStore.setProgress(factoryController.getCarStorage().getOccupancy());
                    bodyStore.setProgress(factoryController.getCarBodyStorage().getOccupancy());
                    accessoriesStore.setProgress(factoryController.getAccessoriesStorage().getOccupancy());
                    engineStore.setProgress(factoryController.getEngineStorage().getOccupancy());
                });
            }
        };
        upd.schedule(task, 0, PERIOD);

    }

    private void addSellCarButton() {
        Button b = new Button(PLUS);
        b.setLayoutX(BUTTON_X);
        b.setLayoutY(BUTTON_Y);

        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                factoryViewController.addSellCarTask();
            }
        });

        anchorPane.getChildren().add(b);
    }

    private void createBackground() {
        Image backgroundImage = new Image(BG, 0, 0, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        anchorPane.setBackground(new Background(background));
    }
}

