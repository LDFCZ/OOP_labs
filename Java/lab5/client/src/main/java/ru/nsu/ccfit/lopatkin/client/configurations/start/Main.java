package ru.nsu.ccfit.lopatkin.client.configurations.start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.nsu.ccfit.lopatkin.client.configurations.SessionConfiguration;

import java.io.IOException;

public class Main {//extends Application {
    //@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/start_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MultiUserChat");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SessionConfiguration.class);
        //Session session = context.getBean(Session.class);
        //session.setUserId(10);
        //Test test = new Test();
        //test.test();
        //launch();
    }
}