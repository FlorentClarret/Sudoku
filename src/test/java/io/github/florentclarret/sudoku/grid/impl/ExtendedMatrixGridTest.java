package io.github.florentclarret.sudoku.grid.impl;

import io.github.florentclarret.sudoku.grid.AbstractSudokuGridTest;

public class ExtendedMatrixGridTest extends AbstractSudokuGridTest<MatrixGrid> {

    public ExtendedMatrixGridTest() {
        super(MatrixGrid::new, 15);
    }
}
