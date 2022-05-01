module ru.nsu.ccfit.lopatkin.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.ccfit.lopatkin.client to javafx.fxml;
    exports ru.nsu.ccfit.lopatkin.client;
}