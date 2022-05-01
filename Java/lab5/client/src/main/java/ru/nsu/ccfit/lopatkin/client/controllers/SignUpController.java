package ru.nsu.ccfit.lopatkin.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label exceptionLabel;

    @FXML
    private PasswordField firstPassword;

    @FXML
    private PasswordField secondPassword;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField userName;

    @FXML
    void signIn(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert exceptionLabel != null : "fx:id=\"exceptionLabel\" was not injected: check your FXML file 'signup_page.fxml'.";
        assert firstPassword != null : "fx:id=\"firstPassword\" was not injected: check your FXML file 'signup_page.fxml'.";
        assert secondPassword != null : "fx:id=\"secondPassword\" was not injected: check your FXML file 'signup_page.fxml'.";
        assert signUpButton != null : "fx:id=\"signUpButton\" was not injected: check your FXML file 'signup_page.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'signup_page.fxml'.";

    }

}
