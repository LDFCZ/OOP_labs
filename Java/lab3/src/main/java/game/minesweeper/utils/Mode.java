package game.minesweeper.utils;

public enum Mode {

    SMALL(9,1),
    MEDIUM(16,35),
    HARD(16,100);

    final int fieldSize;
    final int mineCount;

    Mode(int fieldSize, int mineCount) {
        this.fieldSize = fieldSize;
        this.mineCount = mineCount;
    }

    public Integer getFieldSize() {
        return fieldSize;
    }

    public Integer getMineCount() {
        return mineCount;
    }
}
