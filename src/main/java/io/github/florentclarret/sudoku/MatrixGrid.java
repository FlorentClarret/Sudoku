package io.github.florentclarret.sudoku;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public class MatrixGrid implements SudokuGrid {

    private int[][] grid;

    private int size;

    public MatrixGrid(final int size) {
        this(size, new int[size][size]);
    }

    public MatrixGrid(final int size, final int[][] grid) {
        if(size < 1) {
            throw new IllegalArgumentException("the size must be strictly positive");
        }

        this.grid = Objects.requireNonNull(grid);
        this.size = size;

        if(grid.length != size) {
            throw new IllegalArgumentException("the grid must be " + size + "x" + size);
        }

        for(int row = 0; row < size; ++row) {
            if(Objects.requireNonNull(grid).length != size) {
                throw new IllegalArgumentException("the grid must be " + size + "x" + size);
            }

            System.arraycopy(grid[row], 0, this.grid[row], 0, size);
        }
    }

    @Override
    public int getValue(final int row, final int column) {
        this.checkCoordinates(row, column);
        return grid[row][column];
    }

    @Override
    public void setValue(final int row, final int column, final int value) {
        if(value < 0 || value >= size) {
            throw new IllegalArgumentException(String.format("Invalid value [%d] for a grid [%dx%d", value, size, size));
        }

        this.checkCoordinates(row, column);
        this.grid[row][column] = value;
    }

    @Override
    public void reset(final int row, final int column) {
        this.checkCoordinates(row, column);
        this.grid[row][column] = UNDEFINED_VALUE;
    }

    @Override
    public void resetAll() {
        for(final int[] columns : this.grid) {
            Arrays.fill(columns, UNDEFINED_VALUE);
        }
    }

    private void checkCoordinates(final int row, final int column) {
        if(!(row >= 0 && row < size && column >= 0 && column < size)) {
            throw new IllegalArgumentException(String.format("Invalid coordinates [%dx%d] for a grid [%dx%d", row, column, size, size));
        }
    }
}
