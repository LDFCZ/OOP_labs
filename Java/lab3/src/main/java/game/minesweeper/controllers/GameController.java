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
    private final GameModel gameModel;
    private final Mode mode;
    private final String playerName;


    private final Vector<Vector<Integer>> backEndField;
    private final  Vector<Vector<String>> frontEndField;

    private Boolean isGameStarts = false;
    private Boolean endGame = false;

    private static final String STAT_PATH = "src/main/resources/data.csv";
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
                frontEndField.get(i).add("#");
            }
        }
        gameModel.setMode(mode);
        gameModel.setFields(backEndField, frontEndField);
    }

    public void endGame() {
        endGame = true;
    }

    public MoveResults setFlag(int x, int y) {
        if (endGame) return MoveResults.LOSE;
        if (frontEndField.get(x).get(y).equals("F"))
            frontEndField.get(x).set(y, "#");
        else if (frontEndField.get(x).get(y).equals("#"))
            frontEndField.get(x).set(y, "F");
        else return MoveResults.EMPTY;

        gameModel.setFields(backEndField, frontEndField);
        return  checkWin() ? MoveResults.WIN : MoveResults.EMPTY;
    }

    public MoveResults openCell(int x, int y) {
        if (endGame) return MoveResults.LOSE;
        if (!frontEndField.get(x).get(y).equals("#"))
            return MoveResults.EMPTY;
        if (backEndField.get(x).get(y) == 10) { // if mine
            openMinesCells();
            gameModel.setFields(backEndField, frontEndField);
            return MoveResults.LOSE;
        }
        if (frontEndField.get(x).get(y).equals("F")) // if flag
            return MoveResults.EMPTY;
        if (!isGameStarts) {
            setMines(x, y);
            isGameStarts = true;
        }
        openEmptyCells(x, y);
        gameModel.setFields(backEndField, frontEndField);
        return  checkWin() ? MoveResults.WIN : MoveResults.EMPTY;
    }

    private Boolean checkWin() {
        for (int i = 0; i < mode.getFieldSize(); i++) {
            for (int j = 0; j < mode.getFieldSize(); j++) {
                if (frontEndField.get(i).get(j).equals("#"))
                    return false;
            }
        }
        return true;
    }

    private void openMinesCells() {
        for (int i = 0; i < mode.getFieldSize(); i++) {
            for (int j = 0; j < mode.getFieldSize(); j++) {
                if (backEndField.get(i).get(j) == 10)
                    frontEndField.get(i).set(j, "*");
            }
        }
    }

    private Boolean isValid(int x, int y) {
        return  x >= 0 && x < mode.getFieldSize()  && y >= 0 && y < mode.getFieldSize() && frontEndField.get(x).get(y).equals("#");
    }

    private void openEmptyCells(int x, int y) {
        frontEndField.get(x).set(y, String.valueOf(backEndField.get(x).get(y)));
        if (backEndField.get(x).get(y) != 0) {
            return;
        }

        if (isValid(x - 1, y))
        openEmptyCells(x - 1, y);

        if (isValid(x, y - 1))
            openEmptyCells(x, y - 1);

        if (isValid(x + 1, y))
        openEmptyCells(x + 1, y);

        if (isValid(x, y + 1))
            openEmptyCells(x, y + 1);

    }

    private void setMines(int x, int y) {
        Vector<Integer> positions = new Vector<>();
        for (int i = 0; i < mode.getFieldSize() * mode.getFieldSize(); i++) positions.add(i);

        positions.remove(x * mode.getFieldSize() + y);

        for(int i = 0; i < mode.getMineCount(); i++) {
            int pos = positions.remove((int)(Math.random() * positions.size()));
            x = pos / mode.getFieldSize();
            y = pos % mode.getFieldSize();

            if (x == 0 && y == 0) {
                backEndField.get(x).set(y + 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x + 1).set(y + 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x + 1).set(y, backEndField.get(x).get(y) + 1);
            }
            else if (x == mode.getFieldSize() - 1 && y == mode.getFieldSize() - 1) {
                backEndField.get(x).set(y - 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x - 1).set(y - 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x - 1).set(y, backEndField.get(x).get(y) + 1);
            }
            else if (x == 0) {
                backEndField.get(x).set(y - 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x + 1).set(y - 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x).set(y + 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x + 1).set(y + 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x + 1).set(y, backEndField.get(x).get(y) + 1);
            } else if (y == 0) {
                backEndField.get(x-1).set(y, backEndField.get(x).get(y) + 1);
                backEndField.get(x - 1).set(y + 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x).set(y + 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x + 1).set(y + 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x + 1).set(y, backEndField.get(x).get(y) + 1);
            } else if (x == mode.getFieldSize() - 1) {
                backEndField.get(x).set(y - 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x - 1).set(y - 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x - 1).set(y, backEndField.get(x).get(y) + 1);
                backEndField.get(x - 1).set(y + 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x).set(y + 1, backEndField.get(x).get(y) + 1);
            } else if (y == mode.getFieldSize() - 1) {
                backEndField.get(x + 1).set(y, backEndField.get(x).get(y) + 1);
                backEndField.get(x + 1).set(y - 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x).set(y - 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x - 1).set(y - 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x - 1).set(y, backEndField.get(x).get(y) + 1);
            }
            else {
                backEndField.get(x + 1).set(y, backEndField.get(x).get(y) + 1);
                backEndField.get(x + 1).set(y - 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x).set(y + 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x - 1).set(y + 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x - 1).set(y, backEndField.get(x).get(y) + 1);
                backEndField.get(x - 1).set(y - 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x).set(y - 1, backEndField.get(x).get(y) + 1);
                backEndField.get(x + 1).set(y + 1, backEndField.get(x).get(y) + 1);
            }
            backEndField.get(x).set(y, 10); // set mine
            gameModel.setFields(backEndField, frontEndField);
        }
    }

    public void writeStat() {
        String line = "";
        ArrayList<Pair<String ,Integer>> inData = new ArrayList<>();
        boolean flag = false;
        try (BufferedReader br = new BufferedReader(new FileReader(STAT_PATH))) {
            while ((line = br.readLine()) != null) {

                String[] data = line.split(CSV_SEPARATOR);
                if (data[ConstSpace.NAME].equals(playerName)) {
                    data[ConstSpace.COUNT] = String.valueOf(Integer.parseInt(data[ConstSpace.COUNT]) + 1);
                    flag = true;
                }
                inData.add(new Pair<>(data[ConstSpace.NAME], Integer.parseInt(data[ConstSpace.COUNT])));
            }

        } catch (Exception e) {
            System.out.print("database problems\n");
        }

        if (!flag) inData.add(new Pair<>(playerName, 1));

        inData.sort(comparing(Pair::getValue));
        Collections.reverse(inData);


        try (FileWriter fileWriter = new FileWriter(STAT_PATH);) {
            for (Pair<String ,Integer> p: inData ) {

                String result_CSV_line = p.getKey() + CSV_SEPARATOR + p.getValue();
                fileWriter.write(result_CSV_line + System.lineSeparator());
            }
            fileWriter.flush();
        } catch (Exception e) {
            System.out.print("database problems\n");
        }
    }

}
