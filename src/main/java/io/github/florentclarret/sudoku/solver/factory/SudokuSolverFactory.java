package io.github.florentclarret.sudoku.solver.factory;

import io.github.florentclarret.sudoku.solver.SudokuSolver;
import io.github.florentclarret.sudoku.solver.impl.BacktrackSolver;

import java.util.Objects;

public final class SudokuSolverFactory {

    public SudokuSolverFactory () {
        throw new IllegalArgumentException("no instance for you");
    }

    public static SudokuSolver build(final SudokuSolverType type) {
        switch (Objects.requireNonNull(type, "type can not be null")) {
            case BACKTRACK:
                return new BacktrackSolver();
            default:
                throw new IllegalArgumentException(String.format("SudokuSolverType [%s] is unknown", type));
        }
    }
}
