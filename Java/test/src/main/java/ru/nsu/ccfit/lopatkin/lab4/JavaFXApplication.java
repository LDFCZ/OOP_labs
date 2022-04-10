package ru.nsu.ccfit.lopatkin.lab4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import ru.nsu.ccfit.lopatkin.lab4.GUI.views.FactoryView;
import ru.nsu.ccfit.lopatkin.lab4.factory.FactoryController;
import ru.nsu.ccfit.lopatkin.lab4.factory.FactoryCreator;

public class JavaFXApplication extends Application {
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(MainApplication.class)
                .run(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        //CarFactory factory = new CarFactory();
        FactoryCreator fc = new FactoryCreator();
        FactoryController fcon = new FactoryController(fc.getAccessoriesStorage(), fc.getCarBodyStorage(), fc.getEngineStorage(), fc.getCarStorage(), fc.getWorkerThreadPool(), 3000);
        Thread fcoT = new Thread(fcon);
        fcoT.start();
        FactoryView view = new FactoryView(stage, fc, fcon);
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
}
