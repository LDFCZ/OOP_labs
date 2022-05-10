package ru.nsu.ccfit.lopatkin.client.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    void initialize() {
        assert chatPane != null : "fx:id=\"chatPane\" was not injected: check your FXML file 'chat_page.fxml'.";
        assert getUserListButton != null : "fx:id=\"getUserListButton\" was not injected: check your FXML file 'chat_page.fxml'.";
        assert messageField != null : "fx:id=\"messageField\" was not injected: check your FXML file 'chat_page.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'chat_page.fxml'.";
        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'chat_page.fxml'.";

        Task<VBox> message = new Task<VBox>() {
            @Override
            protected VBox call() throws Exception {
                Label name= new Label("TestName");
                BubbleMessages text = new BubbleMessages("test\ntext 123321123321 вававава", "11:12");
                VBox v = new VBox();
                v.getChildren().addAll(name, text);
                return v;
            }
        };
        Task<VBox> message2 = new Task<VBox>() {
            @Override
            protected VBox call() throws Exception {
                Label name= new Label("TestName");
                BubbleMessages text = new BubbleMessages("test2\ntext 123321123321 вававава", "11:12");
                VBox v = new VBox();
                v.getChildren().addAll(name, text);
                return v;
            }
        };

        message.setOnSucceeded(event -> chatPane.getItems().add(message.getValue()));
        Thread t2 = new Thread(message);
        t2.setDaemon(true);
        t2.start();
        message2.setOnSucceeded(event -> chatPane.getItems().add(message2.getValue()));
        Thread t1 = new Thread(message2);
        t1.setDaemon(true);
        t1.start();

    }

}
