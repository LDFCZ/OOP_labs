package ru.nsu.ccfit.lopatkin.client.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.utils.BubbleMessages;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("../views/chat_page.fxml")
public class ChatController {

    @FXML
    private ResourceBundle resources;

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

    }

    @FXML
    void sendMessage(ActionEvent event) {

    }
    public void addMessage(String name, String text, String time, boolean isMy) {
        Task<VBox> message = new Task<VBox>() {
            @Override
            protected VBox call() throws Exception {
                Label n = new Label(name);
                BubbleMessages t = new BubbleMessages(text, time);
                VBox v = new VBox();
                v.getChildren().addAll(n, t);
                if(isMy)
                    v.setAlignment(Pos.BASELINE_RIGHT);
                else
                    v.setAlignment(Pos.BASELINE_LEFT);
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
        assert chatPane != null : "fx:id=\"chatPane\" was not injected: check your FXML file 'chat_page.fxml'.";
        assert getUserListButton != null : "fx:id=\"getUserListButton\" was not injected: check your FXML file 'chat_page.fxml'.";
        assert messageField != null : "fx:id=\"messageField\" was not injected: check your FXML file 'chat_page.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'chat_page.fxml'.";
        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'chat_page.fxml'.";
    }

}
