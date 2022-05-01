package ru.nsu.ccfit.lopatkin.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logInButton;

    @FXML
    private Button signInButton;

    @FXML
    void logIn(ActionEvent event) {

    }

    @FXML
    void signIn(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert logInButton != null : "fx:id=\"logInButton\" was not injected: check your FXML file 'start_page.fxml'.";
        assert signInButton != null : "fx:id=\"signInButton\" was not injected: check your FXML file 'start_page.fxml'.";

    }

}
