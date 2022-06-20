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

    public static final String REGEX = "^[a-zA-Z0-9_]{3,20}$";
    public static final String PASSWORDS_ARE_NOT_EQUAL = "passwords are not equal!";
    public static final String BAD_NAME = "bad name!";
    public static final String FX_ID_BACK_BUTTON_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_SIGNUP_PAGE_FXML = "fx:id=\"backButton\" was not injected: check your FXML file 'signup_page.fxml'.";
    public static final String FX_ID_EXCEPTION_LABEL_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_SIGNUP_PAGE_FXML = "fx:id=\"exceptionLabel\" was not injected: check your FXML file 'signup_page.fxml'.";
    public static final String FX_ID_FIRST_PASSWORD_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_SIGNUP_PAGE_FXML = "fx:id=\"firstPassword\" was not injected: check your FXML file 'signup_page.fxml'.";
    public static final String FX_ID_SECOND_PASSWORD_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_SIGNUP_PAGE_FXML = "fx:id=\"secondPassword\" was not injected: check your FXML file 'signup_page.fxml'.";
    public static final String FX_ID_SIGN_UP_BUTTON_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_SIGNUP_PAGE_FXML = "fx:id=\"signUpButton\" was not injected: check your FXML file 'signup_page.fxml'.";
    public static final String FX_ID_USER_NAME_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_SIGNUP_PAGE_FXML = "fx:id=\"userName\" was not injected: check your FXML file 'signup_page.fxml'.";
    private FxWeaver fxWeaver;
    private GetRequestFactory getRequestFactory;

    private Session session;

    private boolean isAnswered = false;

    private Timer timer = new Timer();

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
    public SignUpController(FxWeaver fxWeaver, @Lazy GetRequestFactory getRequestFactory, Session session) {
        this.fxWeaver = fxWeaver;
        this.getRequestFactory = getRequestFactory;
        this.session = session;
    }

    private boolean checkNewUserContext(String n, String p1, String p2) {
        if (!p1.equals(p2) || p1.length() == 0 ) {
            Platform.runLater(() -> { exceptionLabel.setText(PASSWORDS_ARE_NOT_EQUAL);});
            return false;
        }

        Pattern p = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(n);

        if (!m.matches()) {
            Platform.runLater(() -> { exceptionLabel.setText(BAD_NAME);});
            return false;
        }
        if (n.length() > Consts.MAX_L || n.length() < Consts.MIN_L) {
            Platform.runLater(() -> {
                exceptionLabel.setText(BAD_NAME);
            });
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
        session.setName(name);
        timer = new Timer();

        TimeOutTask timeOutTask = new TimeOutTask(Thread.currentThread(), timer, () -> {
            if(!SignUpController.this.isAnswered) SignUpController.this.setTimeOut();
            SignUpController.this.isAnswered = false;
        });

        try {
            timer.schedule(timeOutTask, Consts.DELAY);
            getRequest.handleRequest();

        } catch (SocketSendMessageException e) {
            Platform.runLater(() -> { exceptionLabel.setText(e.getMessage());});
        }
    }

    public void setAuthorized() {
        setAnswered();
        timer.cancel();
        signUpButton.getScene().setRoot(fxWeaver.loadView(ChatController.class));
    }

    public void setBadData(String message) {
        setAnswered();
        timer.cancel();
        Platform.runLater(() -> { exceptionLabel.setText(message);});
    }

    public void setTimeOut() {
        Platform.runLater(() -> { exceptionLabel.setText(Consts.REQUEST_TIME_OUT);});
    }

    @FXML
    void turnToStartPage(ActionEvent event) {
        backButton.getScene().setRoot(fxWeaver.loadView(StartController.class));
    }

    public void setAnswered() {
        isAnswered = true;
    }

    @FXML
    void initialize() {
        assert backButton != null : FX_ID_BACK_BUTTON_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_SIGNUP_PAGE_FXML;
        assert exceptionLabel != null : FX_ID_EXCEPTION_LABEL_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_SIGNUP_PAGE_FXML;
        assert firstPassword != null : FX_ID_FIRST_PASSWORD_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_SIGNUP_PAGE_FXML;
        assert secondPassword != null : FX_ID_SECOND_PASSWORD_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_SIGNUP_PAGE_FXML;
        assert signUpButton != null : FX_ID_SIGN_UP_BUTTON_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_SIGNUP_PAGE_FXML;
        assert userName != null : FX_ID_USER_NAME_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_SIGNUP_PAGE_FXML;

    }

}
