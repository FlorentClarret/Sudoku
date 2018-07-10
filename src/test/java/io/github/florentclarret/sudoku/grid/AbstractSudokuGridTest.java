package io.github.florentclarret.sudoku.grid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.function.Function;

public class AbstractSudokuGridTest<T extends AbstractSudokuGrid> extends SudokuGridTest<T> {

    private final Function<Integer, T> creator;

    public AbstractSudokuGridTest(final Function<Integer, T> creator, final int size) {
        super(size);
        this.creator = creator;
    }

    @Override
    public T buildGrid() {
        return creator.apply(size);
    }

    @Test
    public void testConstruct_WithSizeLessThanNine() {
        for (int size = -1; size < 9; ++size) {
            final int finalSize = size;
            Assertions.assertThrows(IllegalArgumentException.class, () -> creator.apply(finalSize), "the size must be greater than 9");
        }
    }
}
