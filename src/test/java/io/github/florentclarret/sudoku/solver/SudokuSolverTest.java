package io.github.florentclarret.sudoku.solver;

import org.junit.jupiter.api.BeforeEach;

public abstract class SudokuSolverTest<T extends SudokuSolver> {

    protected T solver;

    public abstract T buildSolver();

    @BeforeEach
    public void beforeEach() {
        solver = buildSolver();
    }
}
