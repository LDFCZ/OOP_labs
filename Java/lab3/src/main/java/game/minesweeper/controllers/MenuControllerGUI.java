package game.minesweeper.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class MenuControllerGUI implements Initializable {

    @FXML
    public AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    private Button exitButton;

    @FXML
    private Button scoreButton;

    @FXML
    private Button startButton;

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void showScore(ActionEvent event) {

    }

    @FXML
    void startGame(ActionEvent event) {

    }


}
