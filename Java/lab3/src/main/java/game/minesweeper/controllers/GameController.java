package game.minesweeper.controllers;

import game.minesweeper.constspace.ConstSpace;
import game.minesweeper.models.GameModel;
import game.minesweeper.utils.Mode;
import game.minesweeper.utils.MoveResults;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import static java.util.Comparator.comparing;

public class GameController {
    public static final String FLAG = "F";
    public static final String CLOSE = "#";
    public static final int MINE = 10;
    public static final String MINE_STR = "*";
    public static final int SHIFT = 1;

    private final GameModel gameModel;
    private final Mode mode;
    private final String playerName;


    private final Vector<Vector<Integer>> backEndField;
    private final  Vector<Vector<String>> frontEndField;

    private Boolean isGameStarts = false;
    private Boolean endGame = false;

    private static final String CSV_SEPARATOR = ",";


    public GameController(GameModel gameModel, Mode mode, String playerName) {
        this.gameModel = gameModel;
        this.mode = mode;
        this.playerName = playerName;
        backEndField = new Vector<>();
        frontEndField = new Vector<>();
        for (int i = 0; i < mode.getFieldSize(); i++) {
            backEndField.add(new Vector<>());
            frontEndField.add(new Vector<>());
            for (int j = 0; j < mode.getFieldSize(); j++) {
                backEndField.get(i).add(0);
                frontEndField.get(i).add(CLOSE);
            }
        }
        gameModel.setMode(mode);
        gameModel.setFields(frontEndField);
    }

    public void endGame() {
        endGame = true;
    }

    public MoveResults setFlag(int x, int y) {
        if (endGame) return MoveResults.LOSE;
        if (frontEndField.get(x).get(y).equals(FLAG))
            frontEndField.get(x).set(y, CLOSE);
        else if (frontEndField.get(x).get(y).equals(CLOSE))
            frontEndField.get(x).set(y, FLAG);
        else return MoveResults.EMPTY;

        gameModel.setFields(frontEndField);
        return  checkWin() ? MoveResults.WIN : MoveResults.EMPTY;
    }

    public MoveResults openCell(int x, int y) {
        if (endGame) return MoveResults.LOSE;
        if (!frontEndField.get(x).get(y).equals(CLOSE))
            return MoveResults.EMPTY;
        if (backEndField.get(x).get(y) == MINE) { // if mine
            openMinesCells();
            gameModel.setFields(frontEndField);
            return MoveResults.LOSE;
        }
        if (frontEndField.get(x).get(y).equals(FLAG)) // if flag
            return MoveResults.EMPTY;
        if (!isGameStarts) {
            setMines(x, y);
            isGameStarts = true;
        }
        openEmptyCells(x, y);
        gameModel.setFields(frontEndField);
        return  checkWin() ? MoveResults.WIN : MoveResults.EMPTY;
    }

    private Boolean checkWin() {
        int flagCounter = 0;
        for (int i = 0; i < mode.getFieldSize(); i++) {
            for (int j = 0; j < mode.getFieldSize(); j++) {
                if (frontEndField.get(i).get(j).equals(CLOSE))
                    return false;
                if (frontEndField.get(i).get(j).equals(FLAG))
                    flagCounter++;
            }
        }
        if (flagCounter != mode.getMineCount())
            return false;
        return true;
    }

    private void openMinesCells() {
        for (int i = 0; i < mode.getFieldSize(); i++) {
            for (int j = 0; j < mode.getFieldSize(); j++) {
                if (backEndField.get(i).get(j) == MINE)
                    frontEndField.get(i).set(j, MINE_STR);
            }
        }
    }

    private Boolean isValid(int x, int y) {
        return  x >= 0 && x < mode.getFieldSize()  && y >= 0 && y < mode.getFieldSize() && frontEndField.get(x).get(y).equals(CLOSE);
    }

    private void openEmptyCells(int x, int y) {
        frontEndField.get(x).set(y, String.valueOf(backEndField.get(x).get(y)));
        if (backEndField.get(x).get(y) != 0) {
            return;
        }

        if (isValid(x - SHIFT, y))
        openEmptyCells(x - SHIFT, y);

        if (isValid(x, y - SHIFT))
            openEmptyCells(x, y - SHIFT);

        if (isValid(x + SHIFT, y))
        openEmptyCells(x + SHIFT, y);

        if (isValid(x, y + SHIFT))
            openEmptyCells(x, y + SHIFT);

    }

    private void setMines(int x, int y) {
        Vector<Integer> positions = new Vector<>();
        Vector<Integer> minePositions = new Vector<>();
        for (int i = 0; i < mode.getFieldSize() * mode.getFieldSize(); i++) positions.add(i);

        positions.remove(x * mode.getFieldSize() + y);

        for(int i = 0; i < mode.getMineCount(); i++) {
            int pos = positions.remove((int)(Math.random() * positions.size()));
            minePositions.add(pos);
            x = pos / mode.getFieldSize();
            y = pos % mode.getFieldSize();

            try {
                backEndField.get(x + SHIFT).set(y, backEndField.get(x + SHIFT).get(y) + SHIFT);
            } catch (Exception ignored) {}

            try {
                backEndField.get(x + SHIFT).set(y - SHIFT, backEndField.get(x + SHIFT).get(y - SHIFT) + SHIFT);
            } catch (Exception ignored) {}

            try {
                backEndField.get(x).set(y - SHIFT, backEndField.get(x).get(y - SHIFT) + SHIFT);
            } catch (Exception ignored) {}

            try {
                backEndField.get(x - SHIFT).set(y - SHIFT, backEndField.get(x - SHIFT).get(y - SHIFT) + SHIFT);
            } catch (Exception ignored) {}

            try {
                backEndField.get(x - SHIFT).set(y, backEndField.get(x - SHIFT).get(y) + SHIFT);
            } catch (Exception ignored) {}

            try {
                backEndField.get(x - SHIFT).set(y + SHIFT, backEndField.get(x - SHIFT).get(y + SHIFT) + SHIFT);
            } catch (Exception ignored) {}

            try {
                backEndField.get(x).set(y + SHIFT, backEndField.get(x).get(y + SHIFT) + SHIFT);
            } catch (Exception ignored) {}

            try {
                backEndField.get(x + SHIFT).set(y + SHIFT, backEndField.get(x + SHIFT).get(y + SHIFT) + SHIFT);
            } catch (Exception ignored) {}
        }
        for (Integer minePosition : minePositions) {
            x = minePosition / mode.getFieldSize();
            y = minePosition % mode.getFieldSize();
            backEndField.get(x).set(y, MINE); // set mine
        }
        gameModel.setFields(frontEndField);
    }

    public void writeStat() {
        String line = "";
        ArrayList<Pair<String ,Integer>> inData = new ArrayList<>();
        boolean flag = false;
        try (BufferedReader br = new BufferedReader(new FileReader(ConstSpace.STAT_PATH))) {
            while ((line = br.readLine()) != null) {

                String[] data = line.split(CSV_SEPARATOR);
                if (data[ConstSpace.NAME].equals(playerName)) {
                    data[ConstSpace.COUNT] = String.valueOf(Integer.parseInt(data[ConstSpace.COUNT]) + SHIFT);
                    flag = true;
                }
                inData.add(new Pair<>(data[ConstSpace.NAME], Integer.parseInt(data[ConstSpace.COUNT])));
            }

        } catch (Exception e) {
            System.out.print(ConstSpace.DATABASE_PROBLEMS);
        }

        if (!flag) inData.add(new Pair<>(playerName, 1)); //first time play

        inData.sort(comparing(Pair::getValue));
        Collections.reverse(inData);


        try (FileWriter fileWriter = new FileWriter(ConstSpace.STAT_PATH);) {
            for (Pair<String ,Integer> p: inData ) {

                String result_CSV_line = p.getKey() + CSV_SEPARATOR + p.getValue();
                fileWriter.write(result_CSV_line + System.lineSeparator());
            }
            fileWriter.flush();
        } catch (Exception e) {
            System.out.print(ConstSpace.DATABASE_PROBLEMS);
        }
    }

}
