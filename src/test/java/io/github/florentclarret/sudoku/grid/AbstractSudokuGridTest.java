package io.github.florentclarret.sudoku.grid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractSudokuGridTest<T extends AbstractSudokuGrid> extends SudokuGridTest<T> {

    private final Function<Integer, T> creator;

    public AbstractSudokuGridTest(final Function<Integer, T> creator, final int size) {
        super(size);
        this.creator = creator;
    }

    @Override
    public T buildGrid() {
        return creator.apply(size);
    }

    @TestFactory
    public Stream<DynamicTest> testConstruct() {
        final List<Integer> values = Arrays.asList(9, 12, 15, 18, 21, 45);

        return values.stream().map(element -> DynamicTest.dynamicTest("Value=[" + element + "]", () -> {
            T grid = creator.apply(element);
            assertEquals(element.intValue(), grid.getSize());

            for (int row = 0; row < grid.getSize(); ++row) {
                for (int column = 0; column < grid.getSize(); ++column) {
                    assertEquals(SudokuGrid.UNDEFINED_VALUE, grid.getValue(row, column));
                }
            }
        }));
    }

    @Test
    public void testConstruct_WithSizeLessThanNine() {
        for (int size = -1; size < 9; ++size) {
            final int finalSize = size;
            Assertions.assertThrows(IllegalArgumentException.class, () -> creator.apply(finalSize), "the size must "
                    + "be" + " greater than 9");
        }
    }

    @TestFactory
    public Stream<DynamicTest> testConstruct_WithSizeNotDivisibleByThree() {
        final List<Integer> values = Arrays.asList(11, 20, 23, 13);

        return values.stream().map(element -> DynamicTest.dynamicTest("Value=[" + element + "]", () -> assertThrows
                (IllegalArgumentException.class, () -> creator.apply(element), "the size must be divisible by 3")));
    }
}
