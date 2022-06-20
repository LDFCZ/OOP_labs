package ru.nsu.ccfit.lopatkin.client.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.GetRequests.GetRequest;
import ru.nsu.ccfit.lopatkin.client.GetRequests.GetRequestType;
import ru.nsu.ccfit.lopatkin.client.consts.Consts;
import ru.nsu.ccfit.lopatkin.client.exceptions.SocketSendMessageException;
import ru.nsu.ccfit.lopatkin.client.factories.GetRequestFactory;
import ru.nsu.ccfit.lopatkin.client.utils.AskMessageTask;
import ru.nsu.ccfit.lopatkin.client.utils.BubbleMessages;
import ru.nsu.ccfit.lopatkin.client.utils.Session;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@FxmlView("../views/chat_page.fxml")
public class ChatController {

    public static final String ME = "Me";
    public static final String FX_ID_CHAT_PANE_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_CHAT_PAGE_FXML = "fx:id=\"chatPane\" was not injected: check your FXML file 'chat_page.fxml'.";
    public static final String FX_ID_GET_USER_LIST_BUTTON_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_CHAT_PAGE_FXML = "fx:id=\"getUserListButton\" was not injected: check your FXML file 'chat_page.fxml'.";
    public static final String FX_ID_MESSAGE_FIELD_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_CHAT_PAGE_FXML = "fx:id=\"messageField\" was not injected: check your FXML file 'chat_page.fxml'.";
    public static final String FX_ID_NAME_LABEL_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_CHAT_PAGE_FXML = "fx:id=\"nameLabel\" was not injected: check your FXML file 'chat_page.fxml'.";
    public static final String FX_ID_SEND_BUTTON_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_CHAT_PAGE_FXML = "fx:id=\"sendButton\" was not injected: check your FXML file 'chat_page.fxml'.";
    public static final int PERIOD = 500;
    private GetRequestFactory getRequestFactory;
    private Session session;

    @Autowired
    public  ChatController(@Lazy GetRequestFactory getRequestFactory, Session session) {
        this.getRequestFactory = getRequestFactory;
        this.session = session;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private ScrollPane onlineUsersPane;
    @FXML
    private URL location;

    @FXML
    private ListView chatPane;

    @FXML
    private Button getUserListButton;

    @FXML
    private TextArea messageField;

    @FXML
    private Label nameLabel;

    @FXML
    private Button sendButton;

    @FXML
    void getUserList(ActionEvent event) {
        try {
            GetRequest getRequest = getRequestFactory.getGetRequest(GetRequestType.ONLINE_USER_LIST);
            getRequest.handleRequest();
        } catch (SocketSendMessageException e) {
            addMessage("", e.getMessage(), "", true);
        }
    }

    public void addOnlineUsers(List<String> users) {

        Task<AnchorPane> container = new Task<AnchorPane>() {
            @Override
            protected AnchorPane call() throws Exception {
                AnchorPane container = new AnchorPane();
                for (int i = 0; i < users.size(); i++) {
                    Label user = new Label(users.get(i));
                    user.setLayoutX(10);
                    user.setLayoutY(i*20 + 10);
                    container.getChildren().add(user);
                }
                return container;
            }
        };
        container.setOnSucceeded(event -> onlineUsersPane.setContent(container.getValue()));
        Thread t = new Thread(container);
        t.setDaemon(true);
        t.start();
    }
    @FXML
    void sendMessage(ActionEvent event) {
        try {
            GetRequest getRequest = getRequestFactory.getGetRequest(GetRequestType.NEW_MESSAGE);
            getRequest.setState(new ArrayList<String>(List.of(messageField.getText())));
            getRequest.handleRequest();
        } catch (SocketSendMessageException e) {
            addMessage("", e.getMessage(), "", true);
        }
        SimpleDateFormat formatter = new SimpleDateFormat(Consts.TIME_FORMAT);
        addMessage(ME, messageField.getText(), formatter.format(new Date()), true);
        messageField.clear();
    }
    public void addMessage(String name, String text, String time, boolean isMy) {
        Task<VBox> message = new Task<VBox>() {
            @Override
            protected VBox call() throws Exception {
                Label n = new Label(name);
                BubbleMessages t = new BubbleMessages(text, time);
                VBox v = new VBox();
                v.getChildren().addAll(n, t);
                if(isMy) {
                    v.setAlignment(Pos.BASELINE_RIGHT);
                }
                else {
                    v.setAlignment(Pos.BASELINE_LEFT);
                }
                return v;
            }
        };
        message.setOnSucceeded(event -> chatPane.getItems().add(message.getValue()));
        Thread t = new Thread(message);
        t.setDaemon(true);
        t.start();
    }

    @FXML
    void initialize() {
        assert chatPane != null : FX_ID_CHAT_PANE_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_CHAT_PAGE_FXML;
        assert getUserListButton != null : FX_ID_GET_USER_LIST_BUTTON_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_CHAT_PAGE_FXML;
        assert messageField != null : FX_ID_MESSAGE_FIELD_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_CHAT_PAGE_FXML;
        assert nameLabel != null : FX_ID_NAME_LABEL_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_CHAT_PAGE_FXML;
        assert sendButton != null : FX_ID_SEND_BUTTON_WAS_NOT_INJECTED_CHECK_YOUR_FXML_FILE_CHAT_PAGE_FXML;

        Timer t = new Timer();
        t.schedule(new AskMessageTask(Thread.currentThread(), t, getRequestFactory, session), 0, PERIOD);
    }

}
