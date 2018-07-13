package io.github.florentclarret.sudoku.solver.impl;

import io.github.florentclarret.sudoku.solver.SudokuSolverTest;

public class BacktrackSolverTest extends SudokuSolverTest<BacktrackSolver> {

    @Override
    public BacktrackSolver buildSolver() {
        return new BacktrackSolver();
    }
}
