package ru.nsu.ccfit.lopatkin.lab4.views;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ru.nsu.ccfit.lopatkin.lab4.utils.GUI.BlockSubScene;
import ru.nsu.ccfit.lopatkin.lab4.utils.GUI.InfoLabel;
import ru.nsu.ccfit.lopatkin.lab4.utils.GUI.ScrollSubScene;

public class MainView {

    private final AnchorPane anchorPane = new AnchorPane();
    private final Stage stage;

    public MainView (Stage stage) {
        this.stage = stage;
        this.stage.setScene(new Scene(anchorPane, 1635, 900));

        // TODO создание всего и вся
        createBlockSubScenes();
        createScrollSubScenes();
        createBackground();
        this.stage.show();
    }

    private void createScrollSubScenes() {
        ScrollSubScene workersSubScene = new ScrollSubScene(648, 162);

        ScrollSubScene accessoriesSuppliersSubScene = new ScrollSubScene(648, 495);

        ScrollSubScene dealersSubScene = new ScrollSubScene(1097, 400);

        anchorPane.getChildren().addAll(workersSubScene, accessoriesSuppliersSubScene, dealersSubScene);
    }

    private void createBlockSubScenes() {
        BlockSubScene bodySupplier = new BlockSubScene("body supplier",150, 17, 0, 6000);

        BlockSubScene bodyStore = new BlockSubScene("body store", 292, 336);

        BlockSubScene engineSupplier = new BlockSubScene("engine supplier", 574, 17, 0, 6000);

        BlockSubScene engineStore = new BlockSubScene("engine store", 290, 169);

        BlockSubScene accessoriesStore = new BlockSubScene("accessories store", 290, 509);

        BlockSubScene carsStore = new BlockSubScene("ready cars store",1233, 193);

        InfoLabel carsStoreController = new InfoLabel("cars store controller");
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
