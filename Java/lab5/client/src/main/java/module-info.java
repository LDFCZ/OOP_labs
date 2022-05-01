module ru.nsu.ccfit.lopatkin.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.context;


    opens ru.nsu.ccfit.lopatkin.client to javafx.fxml;
    exports ru.nsu.ccfit.lopatkin.client;
    opens ru.nsu.ccfit.lopatkin.client.controllers to javafx.fxml;
    exports ru.nsu.ccfit.lopatkin.client.controllers;
}