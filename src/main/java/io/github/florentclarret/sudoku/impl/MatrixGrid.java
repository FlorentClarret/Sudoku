package io.github.florentclarret.sudoku.impl;

import io.github.florentclarret.sudoku.AbstractSudokuGrid;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public class MatrixGrid extends AbstractSudokuGrid {

    private int[][] grid;

    public MatrixGrid(final int size) {
        this(size, new int[size][size]);
    }

    public MatrixGrid(final int size, final int[][] grid) {
        super(size);

        this.grid = Objects.requireNonNull(grid);

        if (grid.length != size) {
            throw new IllegalArgumentException(String.format("the grid must be [%dx%d]", size, size));
        }

        for (int row = 0; row < size; ++row) {
            if (Objects.requireNonNull(grid).length != size) {
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
        if (value < 0 || value >= super.getSize()) {
            throw new IllegalArgumentException(String.format("Invalid value [%d] for a grid [%dx%d]", value, super
                    .getSize(), super.getSize()));
        }

        super.checkCoordinates(row, column);
        this.grid[row][column] = value;
    }

    @Override
    public void resetAll() {
        Arrays.stream(this.grid).forEach(column -> Arrays.fill(column, UNDEFINED_VALUE));
    }
}
