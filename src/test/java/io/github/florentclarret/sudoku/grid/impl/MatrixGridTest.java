package io.github.florentclarret.sudoku.grid.impl;

import io.github.florentclarret.sudoku.grid.AbstractSudokuGridTest;

public class MatrixGridTest extends AbstractSudokuGridTest<MatrixGrid> {

    public MatrixGridTest() {
        super(MatrixGrid::new, 9);
    }
}
