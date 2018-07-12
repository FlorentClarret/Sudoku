package io.github.florentclarret.sudoku.grid.impl;

import io.github.florentclarret.sudoku.grid.AbstractSudokuGrid;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public class MatrixGrid extends AbstractSudokuGrid {

    private int[][] grid;

    public MatrixGrid(final int size) {
        super(size);
        this.init(new int[size][size]);
    }

    public MatrixGrid(final int size, final int[][] grid) {
        super(size);
        this.init(grid);
    }

    private void init(final int[][] grid) {
        final int size = super.getSize();
        this.grid = Objects.requireNonNull(grid, "the grid must not be null");

        if (grid.length != size) {
            throw new IllegalArgumentException(String.format("the grid must be [%dx%d]", size, size));
        }

        for (int row = 0; row < size; ++row) {
            if (Objects.requireNonNull(grid[row]).length != size) {
                throw new IllegalArgumentException(String.format("the grid must be [%dx%d]", size, size));
            }

            System.arraycopy(grid[row], 0, this.grid[row], 0, size);
        }
    }

    @Override
    public int getValue(final int row, final int column) {
        super.checkCoordinates(row, column);
        return grid[row][column];
    }

    @Override
    public void setValue(final int row, final int column, final int value) {
        super.checkValue(value);
        super.checkCoordinates(row, column);
        this.grid[row][column] = value;
    }

    @Override
    public void resetAll() {
        Arrays.stream(this.grid).forEach(column -> Arrays.fill(column, UNDEFINED_VALUE));
    }
}
