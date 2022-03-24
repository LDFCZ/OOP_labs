package game.minesweeper.controllers;

import game.minesweeper.models.GameModel;
import game.minesweeper.utils.Mode;

public class GameController {
    private final GameModel gameModel;


    public GameController(GameModel gameModel, Mode mode, String playerName) {
        this.gameModel = gameModel;
    }
}
