package game.minesweeper.views.SIS;

import game.minesweeper.controllers.MenuController;
import game.minesweeper.models.GameModel;
import game.minesweeper.utils.Mode;
import game.minesweeper.utils.SIS.Command;
import game.minesweeper.utils.SIS.CommandPane;
import game.minesweeper.utils.SIS.EventHandler;
import game.minesweeper.utils.SIS.Stage;

import java.util.List;

public class MenuViewSIS {
    public static final String START = "START";
    public static final String STATISTIC = "STATISTIC";
    public static final String SETTINGS = "SETTINGS";
    public static final String EXIT = "EXIT";
    public static final String BAD_NAME = "bad name!\n";
    public static final int NAME = 1;
    public static final int ARGS_COUNT = 1;

    private final Stage stage;
    private final CommandPane commandPane = new CommandPane();
    private final GameModel gameModel;
    private final MenuController menuController;

    public MenuViewSIS(Stage stage, GameModel gameModel) {

        this.stage = stage;
        this.gameModel = gameModel;
        menuController = new MenuController(gameModel);
        createCommands();
        stage.setCommandPane(commandPane);
    }

    public void createCommands() {
        Command start = new Command(START, ARGS_COUNT);
        Command stat = new Command(STATISTIC);
        Command settings = new Command(SETTINGS);
        Command exit = new Command(EXIT);

        commandPane.addAllCommand(start, stat, settings, exit);

        start.setOnAction(new EventHandler() {
            @Override
            public void handle(List<String> args) {
                if (!menuController.setPlayerName(args.get(NAME))) {
                    System.out.print(BAD_NAME);
                    return;
                }
                if(gameModel.getMode() == null ) menuController.setFieldParameters(Mode.SMALL);
                GameViewSIS view = new GameViewSIS(stage, gameModel, gameModel.getPlayerName(), gameModel.getMode());
            }
        });

        stat.setOnAction(new EventHandler() {
            @Override
            public void handle(List<String> args) {
                StatViewSIS view = new StatViewSIS(stage, gameModel);
            }
        });

        settings.setOnAction(new EventHandler() {
            @Override
            public void handle(List<String> args) {
                SettingsViewSIS view = new SettingsViewSIS(stage, gameModel);
            }
        });

        exit.setOnAction(new EventHandler() {
            @Override
            public void handle(List<String> args) {
                stage.close();
            }
        });
    }


}
