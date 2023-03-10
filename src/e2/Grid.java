package e2;

import e1.Board;

import java.util.stream.Stream;

public interface Grid extends Board {
    Cell getCell(int x, int y);
    Stream<Cell> stream();
}
