package game.minesweeper.views.SIS;

import game.minesweeper.constspace.ConstSpace;

import game.minesweeper.controllers.MenuController;
import game.minesweeper.models.GameModel;

import game.minesweeper.utils.Mode;
import game.minesweeper.utils.SIS.*;


import java.util.ArrayList;
import java.util.List;

public class SettingsViewSIS {

    public static final String BACK = "BACK";
    public static final String SET = "SET";
    public static final int ARGS_COUNT = 1;
    public static final int MODE = 1;
    public static final int SHIFT = 1;
    public static final String BAD_ARGS = "bad args!\n";
    public static final String X = "X";
    public static final String MINES = " mines: ";

    private final List<Mode> mods = new ArrayList<>();

    private final Stage stage;
    private final CommandPane commandPane = new CommandPane();
    private final GameModel gameModel;
    private final MenuController menuController ;



    public SettingsViewSIS(Stage stage, GameModel gameModel) {
        this.stage = stage;
        this.gameModel = gameModel;
        menuController = new MenuController(gameModel);


        addSettings();
        addCommands();

        stage.setCommandPane(commandPane);
    }

    private void addCommands() {
        Command back = new Command(BACK);
        back.setOnAction(new EventHandler() {
            @Override
            public void handle(List<String> args) {
                MenuViewSIS view = new MenuViewSIS(stage, gameModel);
            }
        });

        Command set = new Command(SET, ARGS_COUNT);
        set.setOnAction(new EventHandler() {
            @Override
            public void handle(List<String> args) {
                try {
                    menuController.setFieldParameters(mods.get(Integer.parseInt(args.get(MODE)) - SHIFT));
                } catch (Exception e) {
                    System.out.print(BAD_ARGS);
                }
            }
        });

        commandPane.addAllCommand(back, set);
    }

    private void addSettings() {
        int i = 0;
        for (Mode mode : Mode.values()) {
            SimpleWidget w = new SimpleWidget( ++i + ConstSpace.BRACKET +
                    mode.getFieldSize().toString() + X + mode.getFieldSize().toString() + MINES +
                    mode.getMineCount().toString());
            commandPane.addWidgets(w);
            mods.add(mode);
        }
    }

}
