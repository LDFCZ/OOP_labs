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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.GetRequests.GetRequest;
import ru.nsu.ccfit.lopatkin.client.GetRequests.GetRequestType;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import ru.nsu.ccfit.lopatkin.client.factories.GetRequestFactory;
import ru.nsu.ccfit.lopatkin.client.utils.TimeOutTask;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@FxmlView("../views/signup_page.fxml")
public class SignUpController {

    private FxWeaver fxWeaver;
    private GetRequestFactory getRequestFactory;


    private Timer timer;

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
    public SignUpController(FxWeaver fxWeaver, @Lazy GetRequestFactory getRequestFactory) {
        this.fxWeaver = fxWeaver;
        this.getRequestFactory = getRequestFactory;
    }

    private boolean checkNewUserContext(String n, String p1, String p2) {
        if (!p1.equals(p2)) {
            exceptionLabel.setText("passwords are not equal!");
            return false;
        }

        Pattern p = Pattern.compile("^[a-zA-Z0-9_]{3,20}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(n);

        if (!m.matches()) {
            exceptionLabel.setText("bad name!");
            return false;
        }
        return true;
    }

    @FXML
    void signIn(ActionEvent event) {
        String name = userName.getText();
        String firstPasswordText = firstPassword.getText();
        String secondPasswordText = secondPassword.getText();

        if (!this.checkNewUserContext(name, firstPasswordText, secondPasswordText)) {
            return;
        }

        GetRequest getRequest = getRequestFactory.getGetRequest(GetRequestType.SIGN_UP);
        ArrayList<String> args = new ArrayList<>();
        args.add(name);
        args.add(firstPasswordText);
        getRequest.setState(args);

        timer = new Timer();
        TimeOutTask timeOutTask = new TimeOutTask(Thread.currentThread(), timer, SignUpController.this::setTimeOut);

        try {
            timer.schedule(timeOutTask, 3000);
            getRequest.handleRequest();

        } catch (SocketSendMessageException e) {
            exceptionLabel.setText(e.getMessage());
        }
    }

    public void setAuthorized() {
        timer.cancel();
        signUpButton.getScene().setRoot(fxWeaver.loadView(ChatController.class));
    }

    public void setBadData(String message) {
        exceptionLabel.setText(message);
    }

    public void setTimeOut() {
        exceptionLabel.setText("Request TimeOut!");
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
