module game.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;


    opens game.minesweeper to javafx.fxml;
    exports game.minesweeper;
    exports game.minesweeper.controllers;
    opens game.minesweeper.controllers to javafx.fxml;
}