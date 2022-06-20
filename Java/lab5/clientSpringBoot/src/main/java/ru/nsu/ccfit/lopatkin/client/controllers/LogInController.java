package ru.nsu.ccfit.lopatkin.client.controllers;

import javafx.application.Platform;
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
import ru.nsu.ccfit.lopatkin.client.consts.Consts;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import ru.nsu.ccfit.lopatkin.client.factories.GetRequestFactory;
import ru.nsu.ccfit.lopatkin.client.utils.Session;
import ru.nsu.ccfit.lopatkin.client.utils.TimeOutHandler;
import ru.nsu.ccfit.lopatkin.client.utils.TimeOutTask;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;

@Component
@FxmlView("../views/login_page.fxml")
public class LogInController {
    public static final String THIS_COULD_NOT_BE_YOUR_NAME = "this could not be your name!";
    public static final String FX_ID_BACK_BUTTON_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_LOGIN_PAGE_FXML = "fx:id=\"backButton\" was not injected: check your FXML file 'login_page.fxml'.";
    public static final String FX_ID_EXCEPTION_LABEL_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_LOGIN_PAGE_FXML = "fx:id=\"exceptionLabel\" was not injected: check your FXML file 'login_page.fxml'.";
    public static final String FX_ID_LOG_IN_BUTTON_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_LOGIN_PAGE_FXML = "fx:id=\"logInButton\" was not injected: check your FXML file 'login_page.fxml'.";
    public static final String FX_ID_PASSWORD_FIELD_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_LOGIN_PAGE_FXML = "fx:id=\"passwordField\" was not injected: check your FXML file 'login_page.fxml'.";
    public static final String FX_ID_USER_NAME_FIELD_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_LOGIN_PAGE_FXML = "fx:id=\"userNameField\" was not injected: check your FXML file 'login_page.fxml'.";
    public static final String NO_PASSWORD = "No password!";


    private Timer timer = new Timer();

    private FxWeaver fxWeaver;
    private GetRequestFactory getRequestFactory;

    private Session session;

    private boolean isAnswered = false;

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
    public LogInController(FxWeaver fxWeaver, @Lazy GetRequestFactory getRequestFactory, Session session) {
        this.fxWeaver = fxWeaver;
        this.getRequestFactory = getRequestFactory;
        this.session = session;
    }

    @FXML
    void logIn(ActionEvent event) {
        String name = userNameField.getText();
        String password = passwordField.getText();

        if (name.length() > Consts.MAX_L || name.length() < Consts.MIN_L) {
            Platform.runLater(() -> { exceptionLabel.setText(THIS_COULD_NOT_BE_YOUR_NAME);});
            return;
        }
        if (password.length() == 0) {
            Platform.runLater(() -> { exceptionLabel.setText(NO_PASSWORD);});
            return;
        }

        GetRequest getRequest = getRequestFactory.getGetRequest(GetRequestType.LOG_IN);
        ArrayList<String> args = new ArrayList<>();
        args.add(name);
        args.add(password);
        getRequest.setState(args);
        session.setName(name);
        timer = new Timer();
        TimeOutTask timeOutTask = new TimeOutTask(Thread.currentThread(), timer, () -> {
            if(!LogInController.this.isAnswered) LogInController.this.setTimeOut();
            LogInController.this.isAnswered = false;
        });

        try {
            timer.schedule(timeOutTask, Consts.DELAY);
            getRequest.handleRequest();
        } catch (SocketSendMessageException e) {
            Platform.runLater(() -> { exceptionLabel.setText(e.getMessage());});
        }
    }

    @FXML
    void turnToStartPage(ActionEvent event) {
        timer.cancel();
        backButton.getScene().setRoot(fxWeaver.loadView(StartController.class));
    }

    public void setAuthorized() {
        setAnswered();
        timer.cancel();
        logInButton.getScene().setRoot(fxWeaver.loadView(ChatController.class));
    }

    public void setTimeOut() {
        Platform.runLater(() -> {exceptionLabel.setText(Consts.REQUEST_TIME_OUT);});
    }

    public void setBadData(String message) {
        setAnswered();
        timer.cancel();
        Platform.runLater(() -> { exceptionLabel.setText(message);});
    }

    public void setAnswered() {
        isAnswered = true;
    }

    @FXML
    void initialize() {
        assert backButton != null : FX_ID_BACK_BUTTON_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_LOGIN_PAGE_FXML;
        assert exceptionLabel != null : FX_ID_EXCEPTION_LABEL_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_LOGIN_PAGE_FXML;
        assert logInButton != null : FX_ID_LOG_IN_BUTTON_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_LOGIN_PAGE_FXML;
        assert passwordField != null : FX_ID_PASSWORD_FIELD_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_LOGIN_PAGE_FXML;
        assert userNameField != null : FX_ID_USER_NAME_FIELD_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_LOGIN_PAGE_FXML;
    }

}
