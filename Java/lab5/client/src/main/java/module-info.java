module ru.nsu.ccfit.lopatkin.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.context;
    requires spring.core;
    requires spring.beans;


    opens ru.nsu.ccfit.lopatkin.client to javafx.fxml;
    exports ru.nsu.ccfit.lopatkin.client;
    opens ru.nsu.ccfit.lopatkin.client.controllers to javafx.fxml;
    exports ru.nsu.ccfit.lopatkin.client.controllers;
    exports ru.nsu.ccfit.lopatkin.client.configurations.start;
    opens ru.nsu.ccfit.lopatkin.client.configurations.start to javafx.fxml;
}