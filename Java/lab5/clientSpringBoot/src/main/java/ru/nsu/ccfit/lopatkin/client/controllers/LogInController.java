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
import ru.nsu.ccfit.lopatkin.client.GetRequests.GetRequest;
import ru.nsu.ccfit.lopatkin.client.GetRequests.GetRequestType;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import ru.nsu.ccfit.lopatkin.client.factories.GetRequestFactory;
import ru.nsu.ccfit.lopatkin.client.utils.TimeOutHandler;
import ru.nsu.ccfit.lopatkin.client.utils.TimeOutTask;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;

@Component
@FxmlView("../views/login_page.fxml")
public class LogInController {

    private boolean isAuthorized = false;

    private boolean isBadData = false;
    private boolean isTimeOut = false;

    private FxWeaver fxWeaver;
    private GetRequestFactory getRequestFactory;

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
    public LogInController(FxWeaver fxWeaver, GetRequestFactory getRequestFactory) {
        this.fxWeaver = fxWeaver;
        this.getRequestFactory = getRequestFactory;
    }

    @FXML
    void logIn(ActionEvent event) {
        String name = userNameField.getText();
        String password = passwordField.getText();

        if (name.length() > 20 || name.length() < 3) {
            exceptionLabel.setText("this could not be your name!");
            return;
        }

        GetRequest getRequest = getRequestFactory.getGetRequest(GetRequestType.LOG_IN);
        ArrayList<String> args = new ArrayList<>();
        args.add(name);
        args.add(password);
        getRequest.setState(args);


        Timer timer = new Timer();
        TimeOutTask timeOutTask = new TimeOutTask(Thread.currentThread(), timer, () -> {
            LogInController.this.notify();
            LogInController.this.setTimeOut();
        });

        try {
            getRequest.handleRequest();
            timer.schedule(timeOutTask, 3000);
            while (!isAuthorized && !isBadData) {
                if (isTimeOut) {
                    exceptionLabel.setText("Request TimeOut!");
                    return;
                }
                wait();
            }
        } catch (SocketSendMessageException e) {
            exceptionLabel.setText(e.getMessage());
            return;
        }
        catch (InterruptedException e) {
            return;
        }
        timer.cancel();
        if (isAuthorized) {
            logInButton.getScene().setRoot(fxWeaver.loadView(ChatController.class));
        }
    }

    @FXML
    void turnToStartPage(ActionEvent event) {
        backButton.getScene().setRoot(fxWeaver.loadView(StartController.class));
    }

    public void setAuthorized() {
        this.isAuthorized = true;
    }

    public void setTimeOut() {
        isTimeOut = true;
    }

    public void setBadData(String message) {
        this.isBadData = true;
        exceptionLabel.setText(message);
    }

    public void setGoodData() {
        this.isBadData = false;
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
