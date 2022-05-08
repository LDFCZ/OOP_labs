package ru.nsu.ccfit.lopatkin.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("../views/signup_page.fxml")
public class SignUpController {

    private FxWeaver fxWeaver;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

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

    @Autowired
    public SignUpController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    void signIn(ActionEvent event) {

    }

    @FXML
    void turnToStartPage(ActionEvent event) {
        backButton.getScene().setRoot(fxWeaver.loadView(StartController.class));
    }

    @FXML
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'signup_page.fxml'.";
        assert exceptionLabel != null : "fx:id=\"exceptionLabel\" was not injected: check your FXML file 'signup_page.fxml'.";
        assert firstPassword != null : "fx:id=\"firstPassword\" was not injected: check your FXML file 'signup_page.fxml'.";
        assert secondPassword != null : "fx:id=\"secondPassword\" was not injected: check your FXML file 'signup_page.fxml'.";
        assert signUpButton != null : "fx:id=\"signUpButton\" was not injected: check your FXML file 'signup_page.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'signup_page.fxml'.";

    }

}
