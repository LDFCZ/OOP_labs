package game.minesweeper.views.SIS;

import game.minesweeper.controllers.GameController;
import game.minesweeper.models.GameModel;
import game.minesweeper.utils.Mode;
import game.minesweeper.utils.MoveResults;
import game.minesweeper.utils.SIS.*;

import java.util.List;
import java.util.Vector;

public class GameViewSIS {

    public static final String SPACE = " ";
    public static final String FLAG = "FLAG";
    public static final String OPEN = "OPEN";
    public static final String BACK = "BACK";
    public static final int Y = 1;
    public static final int SHIFT = 1;
    public static final int X = 2;
    public static final String BAD_ARGS = "bad args!\n";
    public static final String WIN = "WIN!\n";
    public static final int ARGS_COUNT = 2;


    private final Stage stage;
    private final GameModel gameModel;
    private final CommandPane commandPane = new CommandPane();

    private final GameController gameController;

    public GameViewSIS(Stage stage, GameModel gameModel, String playerName, Mode mode) {
        this.stage = stage;
        this.gameModel = gameModel;
        gameController = new GameController(gameModel, mode, playerName);

        addField();
        addCommands();

        stage.setCommandPane(commandPane);
    }

    private void addField() {
        Vector<Vector<String>> field = gameModel.getFrontEndField();
        for (int i = 0; i < gameModel.getFieldSize(); i++) {
            String out = "";
            for (int j = 0; j < gameModel.getFieldSize(); j++) {
                out += field.get(i).get(j) + SPACE;
            }
            SimpleWidget w = new SimpleWidget(out);
            commandPane.addWidgets(w);
        }
    }

    private void updateField() {
        commandPane.clear();
        Vector<Vector<String>> field = gameModel.getFrontEndField();
        for (int i = 0; i < gameModel.getFieldSize(); i++) {
            StringBuilder out = new StringBuilder();
            for (int j = 0; j < gameModel.getFieldSize(); j++) {
                out.append(field.get(i).get(j)).append(SPACE);
            }
            SimpleWidget w = new SimpleWidget(out.toString());
            commandPane.addWidgets(w);
        }
        addCommands();
    }

    private void addCommands() {
        Command flag = new Command(FLAG, ARGS_COUNT);
        Command open = new Command(OPEN, ARGS_COUNT);
        Command back = new Command(BACK);

        commandPane.addAllCommand(open, flag, back);

        flag.setOnAction(new EventHandler() {
            @Override
            public void handle(List<String> args) {
                try {
                    processMove(gameController.setFlag(Integer.parseInt(args.get(X)) - SHIFT, Integer.parseInt(args.get(Y)) - SHIFT));
                    updateField();
                } catch (Exception e) {

                    System.out.print(BAD_ARGS);
                }
            }
        });

        open.setOnAction(new EventHandler() {
            @Override
            public void handle(List<String> args) {
                try {
                    processMove(gameController.openCell(Integer.parseInt(args.get(X)) - SHIFT, Integer.parseInt(args.get(Y)) - SHIFT));
                    updateField();
                } catch (Exception e) {
                    System.out.print(BAD_ARGS);
                }
            }
        });

        back.setOnAction(new EventHandler() {
            @Override
            public void handle(List<String> args) {
                MenuViewSIS view = new MenuViewSIS(stage, gameModel);
            }
        });
    }

    private void processMove(MoveResults result) {
        if (result == MoveResults.WIN) {
            gameController.writeStat();
            System.out.print(WIN);
            MenuViewSIS view = new MenuViewSIS(stage, gameModel);
        } else if (result == MoveResults.LOSE) {
            gameController.endGame();
        }
    }


}
