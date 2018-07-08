package io.github.florentclarret.sudoku;

public interface SudokuGrid {

    int UNDEFINED_VALUE = 0;

    int getValue(final int row, final int column);

    int setValue(final int row, final int column, final int value);

    void reset(final int row, final int column);

    void resetAll();

}
