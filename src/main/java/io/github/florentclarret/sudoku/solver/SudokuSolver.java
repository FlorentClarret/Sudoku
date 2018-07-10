package io.github.florentclarret.sudoku.solver;

import io.github.florentclarret.sudoku.grid.SudokuGrid;

/**
 * Represent a solver for the sudoku game
 */
public interface SudokuSolver {

    /**
     * Solve the given SudokuGrid using a given algorithm.
     * @param grid The grid to solve.
     * @return The solved version of the input grid.
     */
    SudokuGrid solve(final SudokuGrid grid);
}
