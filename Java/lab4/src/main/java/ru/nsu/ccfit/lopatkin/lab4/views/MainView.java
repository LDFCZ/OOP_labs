package ru.nsu.ccfit.lopatkin.lab4.views;


import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ru.nsu.ccfit.lopatkin.lab4.factory.CarFactory;
import ru.nsu.ccfit.lopatkin.lab4.utils.GUI.BlockSlider;
import ru.nsu.ccfit.lopatkin.lab4.utils.GUI.BlockSubScene;
import ru.nsu.ccfit.lopatkin.lab4.utils.GUI.InfoLabel;
import ru.nsu.ccfit.lopatkin.lab4.utils.GUI.ScrollSubScene;

public class MainView {

    private final AnchorPane anchorPane = new AnchorPane();
    private final Stage stage;

    private final CarFactory carFactory;

    public MainView (Stage stage, CarFactory factory) {
        this.stage = stage;
        this.stage.setScene(new Scene(anchorPane, 1635, 900));

        carFactory = factory;

        // TODO создание всего и вся
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

        ScrollSubScene accessoriesSuppliersSubScene = new ScrollSubScene(648, 495);
        createAccessoriesSuppliers(accessoriesSuppliersSubScene);

        BlockSlider accessoriesDelay = new BlockSlider(0,6000);
        accessoriesDelay.setLayoutX(700);
        accessoriesDelay.setLayoutY(455);

        ScrollSubScene dealersSubScene = new ScrollSubScene(1097, 400);
        createDealers(dealersSubScene);

        anchorPane.getChildren().addAll(workersSubScene, accessoriesSuppliersSubScene, dealersSubScene, workersDelay, accessoriesDelay);
    }

    private void createDealers(ScrollSubScene dealersSubScene) {
        final AnchorPane container = new AnchorPane();
        for (int i = 0; i < carFactory.getDealerCount(); i++) {
            BlockSubScene bs = new BlockSubScene("Dealer " + String.valueOf(i + 1), 50, i * 105, 1000, 6000);
            container.getChildren().add(bs);
        }
        dealersSubScene.getPane().setContent(container);
    }

    private void createWorkers(ScrollSubScene workersSubScene) {
        final AnchorPane container = new AnchorPane();
        for (int i = 0; i < carFactory.getWorkerCount(); i++) {
            container.getChildren().add(new BlockSubScene("Worker " + String.valueOf(i + 1), 50, i * 105));
        }
        workersSubScene.getPane().setContent(container);
    }

    private void createAccessoriesSuppliers(ScrollSubScene accessoriesSuppliersSubScene) {
        final AnchorPane container = new AnchorPane();
        for (int i = 0; i < carFactory.getSupplierCount(); i++) {
            container.getChildren().add(new BlockSubScene("Accessory Supplier " + String.valueOf(i + 1), 50, i * 105));
        }
        accessoriesSuppliersSubScene.getPane().setContent(container);
    }

    private void createBlockSubScenes() {
        BlockSubScene bodySupplier = new BlockSubScene("body supplier",150, 17, 0, 6000);

        BlockSubScene bodyStore = new BlockSubScene("body store", 292, 336);

        BlockSubScene engineSupplier = new BlockSubScene("engine supplier", 574, 17, 0, 6000);

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

    }

    private void createBackground() {
        Image backgroundImage = new Image("bg.png", 0, 0, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        anchorPane.setBackground(new Background(background));
    }
}
