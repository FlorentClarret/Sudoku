package io.github.florentclarret.sudoku.grid;

public abstract class AbstractSudokuGrid implements SudokuGrid {

    private final int size;

    public AbstractSudokuGrid(final int size) {
        if(size < 9) {
            throw new IllegalArgumentException("the size must be greater than 9");
        } else if (size % 3 != 0) {
            throw new IllegalArgumentException("the size must be divisible by 3");
        }

        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void reset(final int row, final int column) {
        this.checkCoordinates(row, column);
        this.setValue(row, column, UNDEFINED_VALUE);
    }

    @Override
    public void resetAll() {
        for (int row = 0; row < size; ++row) {
            for (int column = 0; column < size; ++column) {
                this.reset(row, column);
            }
        }
    }

    protected void checkCoordinates(final int row, final int column) {
        if(!(row >= 0 && row < size && column >= 0 && column < size)) {
            throw new IllegalArgumentException(String.format("Invalid coordinates [%dx%d] for a grid [%dx%d]", row,
                    column, size, size));
        }
    }
}
