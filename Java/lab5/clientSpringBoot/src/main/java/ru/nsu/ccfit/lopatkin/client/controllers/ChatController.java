package ru.nsu.ccfit.lopatkin.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

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
    private ListView<?> chatPane;

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

    }

}
