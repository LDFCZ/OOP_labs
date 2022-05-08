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
@FxmlView("../views/login_page.fxml")
public class LogInController {

    private boolean logInFlag = false;
    private long userId = -1;
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
    private Button logInButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userNameField;

    @Autowired
    public LogInController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    void logIn(ActionEvent event) {

    }

    @FXML
    void turnToStartPage(ActionEvent event) {
        backButton.getScene().setRoot(fxWeaver.loadView(StartController.class));
    }

    void allowLogIn(long userId) {
        this.logInFlag = true;
        this.userId = userId;
    }

    @FXML
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'login_page.fxml'.";
        assert exceptionLabel != null : "fx:id=\"exceptionLabel\" was not injected: check your FXML file 'login_page.fxml'.";
        assert logInButton != null : "fx:id=\"logInButton\" was not injected: check your FXML file 'login_page.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'login_page.fxml'.";
        assert userNameField != null : "fx:id=\"userNameField\" was not injected: check your FXML file 'login_page.fxml'.";

    }

}
