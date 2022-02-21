import javafx.application.Application;
import javafx.stage.Stage;
import views.MenuView;

public class Main extends Application{

    private static final String WINDOW_NAME = "ULTRA GAME!";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(WINDOW_NAME);
        MenuView menuView = new MenuView(stage);
    }
}
