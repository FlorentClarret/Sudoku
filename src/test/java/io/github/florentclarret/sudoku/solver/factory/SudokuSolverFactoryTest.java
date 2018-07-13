package io.github.florentclarret.sudoku.solver.factory;

import io.github.florentclarret.sudoku.solver.impl.BacktrackSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuSolverFactoryTest {

    @Test
    public void testBuild() {
        Assertions.assertEquals(BacktrackSolver.class, SudokuSolverFactory.build(SudokuSolverType.BACKTRACK).getClass());
    }

    @Test
    public void testBuild_WithNullInput() {
        Assertions.assertThrows(NullPointerException.class, () -> SudokuSolverFactory.build(null), "type can not be null");
    }

}
