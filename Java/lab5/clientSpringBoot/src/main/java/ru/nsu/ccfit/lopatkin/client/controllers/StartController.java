package ru.nsu.ccfit.lopatkin.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("../views/start_page.fxml")
public class StartController {

    private FxWeaver fxWeaver;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logInButton;

    @FXML
    private Button signInButton;

    @Autowired
    public StartController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }


    @FXML
    void logIn(ActionEvent event) {
        logInButton.getScene().setRoot(fxWeaver.loadView(LogInController.class));
    }

    //Sign up?????
    @FXML
    void signIn(ActionEvent event) {
        signInButton.getScene().setRoot(fxWeaver.loadView(SignUpController.class));
    }

    @FXML
    void initialize() {
        assert logInButton != null : "fx:id=\"logInButton\" was not injected: check your FXML file 'start_page.fxml'.";
        assert signInButton != null : "fx:id=\"signInButton\" was not injected: check your FXML file 'start_page.fxml'.";
    }

}
