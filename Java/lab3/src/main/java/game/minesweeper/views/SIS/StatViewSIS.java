package game.minesweeper.views.SIS;

import game.minesweeper.constspace.ConstSpace;
import game.minesweeper.models.GameModel;
import game.minesweeper.models.StatModel;
import game.minesweeper.utils.SIS.*;

import java.util.List;

public class StatViewSIS {

    public static final String BACK = "BACK";
    public static final String SPACE = " ";

    private final Stage stage;
    private final StatModel statModel = new StatModel();
    private final CommandPane pane = new CommandPane();
    private final GameModel gameModel;

    public StatViewSIS(Stage stage, GameModel gameModel) {
        this.stage = stage;
        this.gameModel = gameModel;
        addStat();

        Command back = new Command(BACK);
        back.setOnAction(new EventHandler() {
            @Override
            public void handle(List<String> args) {
                MenuViewSIS view = new MenuViewSIS(stage, gameModel);
            }
        });
        pane.addCommand(back);
        stage.setCommandPane(pane);
    }

    private void addStat() {
        List<String[]> stat = statModel.getStat();
        int i = 0;
        pane.addWidgets(new SimpleWidget(ConstSpace.STAT_LABEL));
        for (String[] s : stat) {
            if (i == ConstSpace.MAX_TOP_PLAYERS_COUNT) break;
            pane.addWidgets(new SimpleWidget((++i) + ConstSpace.BRACKET + s[ConstSpace.NAME] + SPACE + s[ConstSpace.COUNT]));
        }
    }
}
