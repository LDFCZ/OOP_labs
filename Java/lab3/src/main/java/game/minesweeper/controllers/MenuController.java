package game.minesweeper.controllers;


import game.minesweeper.constspace.ConstSpace;
import game.minesweeper.models.GameModel;
import game.minesweeper.utils.Mode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuController {
    private final GameModel gameModel;

    public MenuController(GameModel gameModel) {
        this.gameModel = gameModel;
    }


    public boolean setPlayerName(String playerName) {
        Pattern p = Pattern.compile(ConstSpace.REGEX, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(playerName);

        if (m.matches()) {
            gameModel.setPlayerName(playerName);
            return true;
        }
        return false;
    }

    public void setFieldParameters(Mode mode) {
        gameModel.setFieldParameters(mode.getFieldSize(), mode.getMineCount());
        gameModel.setMode(mode);
    }

}
