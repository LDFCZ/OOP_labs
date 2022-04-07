module ru.nsu.ccfit.lopatkin.lab4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens ru.nsu.ccfit.lopatkin.lab4 to javafx.fxml;
    exports ru.nsu.ccfit.lopatkin.lab4;
}