module ru.nsu.ccfit.lopatkin.lab4 {
    requires javafx.controls;
    requires javafx.fxml;


    requires java.naming;
    requires log4j;
    requires hibernate.jpa;
    requires hibernate.core;

    opens ru.nsu.ccfit.lopatkin.lab4 to javafx.fxml;
    exports ru.nsu.ccfit.lopatkin.lab4;
}