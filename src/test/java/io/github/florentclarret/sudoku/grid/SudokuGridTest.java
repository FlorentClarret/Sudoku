package io.github.florentclarret.sudoku.grid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Super test class for all the class that test a specific implementation of the SudokuGrid interface.
 * It centralizes all the tests for the methods which should be in the SudokuGrid interface.
 * @param <T> The type of the sudoku grid you're working on.
 */
public abstract class SudokuGridTest<T extends SudokuGrid> {

    /**
     * The grid to test
     */
    protected T grid;

    /**
     * The expected size of the grid
     */
    protected final int size;

    public SudokuGridTest(final int size) {
        this.size = size;
    }

    /**
     * Method which should be implemented by the child classes in order to build a grid.
     * @return A new empty instance of a grid
     */
    public abstract T buildGrid();

    @BeforeEach
    public void beforeEach() {
        grid = buildGrid();

        for (int row = 0; row < grid.getSize(); ++row) {
            for (int column = 0; column < grid.getSize(); ++column) {
                grid.setValue(row, column, ((row + column) % grid.getSize()) + 1);
            }
        }
    }

    @Test
    public void testResetAll() {
        grid.resetAll();

        for (int row = 0; row < grid.getSize(); ++row) {
            for (int column = 0; column < grid.getSize(); ++column) {
                Assertions.assertEquals(SudokuGrid.UNDEFINED_VALUE, grid.getValue(row, column));
            }
        }
    }

    @Test
    public void testReset() {
        for (int row = 0; row < grid.getSize(); ++row) {
            for (int column = 0; column < grid.getSize(); ++column) {
                Assertions.assertNotEquals(SudokuGrid.UNDEFINED_VALUE, grid.getValue(row, column));
                grid.reset(row, column);
                Assertions.assertEquals(SudokuGrid.UNDEFINED_VALUE, grid.getValue(row, column));
            }
        }
    }

    @Test
    public void testSetValue() {
        grid.resetAll();

        for (int row = 0; row < grid.getSize(); ++row) {
            for (int column = 0; column < grid.getSize(); ++column) {
                final int value = ((row + column) % grid.getSize()) + 1;
                grid.setValue(row, column, value);
                Assertions.assertEquals(value, grid.getValue(row, column));
            }
        }
    }

    @Test
    public void testGetSize() {
        Assertions.assertEquals(size, grid.getSize());
    }
}
