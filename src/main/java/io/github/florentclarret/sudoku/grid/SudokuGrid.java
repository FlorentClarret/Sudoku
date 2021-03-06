package io.github.florentclarret.sudoku.grid;

/**
 * Represents a sudoku grid.
 */
public interface SudokuGrid {

    /**
     * The default value stored in a column not set yet.
     */
    int UNDEFINED_VALUE = 0;

    /**
     * Return the value from the specified cell.
     * @param row The row of the cell.
     * @param column The column of the cell.
     * @return The value stored inside the cell.
     * @throws IllegalArgumentException if the coordinates are invalid
     */
    int getValue(final int row, final int column);

    /**
     * Set the value in the given cell.
     * @param row The row of the cell.
     * @param column The column of the cell.
     * @param value The value to store.
     * @throws IllegalArgumentException if the coordinates are invalid
     * @throws IllegalArgumentException if the value is invalid
     */
    void setValue(final int row, final int column, final int value);

    /**
     * Reset the value of the specified cell the the default value.
     * @param row The row of the cell.
     * @param column The column of the cell.
     * @throws IllegalArgumentException if the coordinates are invalid
     */
    void reset(final int row, final int column);

    /**
     * Reset all the value from the grid to the default one.
     */
    void resetAll();

    /**
     * Return the size of the sudoku grid.
     */
    int getSize();

    /**
     * Return whether a grid is still valid or not.
     * @return true if the grid is valid, false otherwise.
     */
    boolean isValid();

    /**
     * Return whether a grid is completed or not.
     * Completed means that the grid is valid and do not contain undefined values.
     * @return true if the grid is completed, false otherwise.
     */
    boolean isCompleted();
}
