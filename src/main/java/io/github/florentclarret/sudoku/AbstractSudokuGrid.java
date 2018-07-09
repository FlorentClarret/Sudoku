package io.github.florentclarret.sudoku;

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

    public int getSize() {
        return size;
    }

    protected void checkCoordinates(final int row, final int column) {
        if(!(row >= 0 && row < size && column >= 0 && column < size)) {
            throw new IllegalArgumentException(String.format("Invalid coordinates [%dx%d] for a grid [%dx%d]", row,
                    column, size, size));
        }
    }
}
