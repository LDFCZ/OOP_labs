package game.minesweeper.models;

import game.minesweeper.utils.Mode;

import java.util.Vector;

public class GameModel {

    private String playerName;

    private Mode mode;

    private int mineCount;
    private int fieldSize;

    private Boolean isFirstStep;

    private Vector<Vector<Integer>> backEndField;
    private Vector<Vector<String>> frontEndField;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setFields(Vector<Vector<Integer>> backEndField, Vector<Vector<String>> frontEndField) {
        this.backEndField = backEndField;
        this.frontEndField = frontEndField;
    }

    public void setFieldParameters(int size, int mineCount) {
        this.mineCount = mineCount;
        this.fieldSize = size;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        this.fieldSize = mode.getFieldSize();
        this.mineCount = mode.getMineCount();
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getMineCount() {
        return mineCount;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Vector<Vector<String>> getFrontEndField() {
        return frontEndField;
    }

    public String getCellCondition(int x, int y) {
        return frontEndField.get(x).get(y);
    }
}
