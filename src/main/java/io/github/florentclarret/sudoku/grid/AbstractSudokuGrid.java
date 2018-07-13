package io.github.florentclarret.sudoku.grid;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Abstract class which represents a sudoku grid
 */
public abstract class AbstractSudokuGrid implements SudokuGrid {

    /**
     * The size of the grid. Most of the time, it's 9.
     */
    private final int size;

    /**
     * Build a 9x9 sudoku grid.
     */
    public AbstractSudokuGrid() {
        this(9);
    }

    /**
     * Build a grid with the given size.
     * @param size The size of the grid.
     */
    public AbstractSudokuGrid(final int size) {
        if(size < 9) {
            throw new IllegalArgumentException("the size must be greater than 9");
        } else if (size % 3 != 0) {
            throw new IllegalArgumentException("the size must be divisible by 3");
        }

        this.size = size;
        this.resetAll();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void reset(final int row, final int column) {
        this.checkCoordinates(row, column);
        this.setValue(row, column, UNDEFINED_VALUE);
    }

    @Override
    public void resetAll() {
        for (int row = 0; row < size; ++row) {
            for (int column = 0; column < size; ++column) {
                this.reset(row, column);
            }
        }
    }

    @Override
    public boolean isValid() {
        for (int index = 0; index < getSize(); ++index) {
            if (!isValidSquare(index) || !isValidRow(index) || !isValidColumn(index)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Allow you to check that a given coordinate is correct for the current grid.
     * @param row The row of the cell to check.
     * @param column The column of the cell to check.
     * @throws IllegalArgumentException if the coordinates are invalid.
     */
    protected void checkCoordinates(final int row, final int column) {
        if(!(row >= 0 && row < size && column >= 0 && column < size)) {
            throw new IllegalArgumentException(String.format("Invalid coordinates [%dx%d] for a grid [%dx%d]", row,
                    column, size, size));
        }
    }

    /**
     * Check the given value is valid for the grid.
     * @param value The value to check.
     * @throws IllegalArgumentException if the value is invalid.
     */
    protected void checkValue(final int value) {
        if (value != UNDEFINED_VALUE && (value < 1 || value > this.getSize())) {
            throw new IllegalArgumentException(String.format("Invalid value [%d] for a grid [%dx%d]", value, this
                    .getSize(), this.getSize()));
        }
    }

    /**
     * Check that a specific row is valid in the grid.
     * @param row The row to check.
     * @return true if the given row is valid, false otherwise
     */
    private boolean isValidRow(final int row) {
        final Set<Integer> rowValues = new HashSet<>(getSize());

        for (int i = 0, size = getSize(); i < size; ++i) {
            final int currentValue = this.getValue(row, i);
            if (currentValue != UNDEFINED_VALUE && !rowValues.add(currentValue)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check that a specific column is valid in the grid.
     * @param column The column to check.
     * @return true if the given column is valid, false otherwise
     */
    private boolean isValidColumn(final int column) {
        final Set<Integer> columnValues = new HashSet<>(getSize());

        for (int i = 0, size = getSize(); i < size; ++i) {
            final int currentValue = this.getValue(i, column);
            if (currentValue != UNDEFINED_VALUE && !columnValues.add(currentValue)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check that a specific square is valid in the grid.
     * The square are numbered from left to right and top to down.
     * @param square The square to check.
     * @return true if the given square is valid, false otherwise
     */
    private boolean isValidSquare(final int square) {
        final Set<Integer> squareValues = new HashSet<>(getSize());
        final int squareSize = getSize() / 3;

        for (int row = (square / 3) * squareSize, maxRox = (square / 3) * squareSize + squareSize; row < maxRox; ++row) {
            for (int column = (square % 3) * squareSize, maxColumn = (square % 3) * squareSize + squareSize; column < maxColumn; ++column) {
                final int currentValue = this.getValue(row, column);
                if (currentValue != UNDEFINED_VALUE && !squareValues.add(currentValue)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        final int separatorIndex = getSize() / 3;
        final StringBuilder stringBuilder = new StringBuilder(300);

        for (int row = 0; row < size; ++row) {
            for (int column = 0; column < size; ++column) {
                stringBuilder.append(String.format("%02d", this.getValue(row, column)))
                        .append(" ");

                if ((column + 1) % separatorIndex == 0 && (column + 1) != getSize()) {
                    stringBuilder.append("| ");
                }
            }

            stringBuilder.append(System.lineSeparator());

            if ((row + 1) % separatorIndex == 0 && (row + 1) != getSize()) {
                stringBuilder.append(Strings.repeat("-", getSize() * 3 + 3))
                    .append(System.lineSeparator());
            }
        }

        return stringBuilder.toString();
    }
}
