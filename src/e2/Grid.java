package e2;

import e1.Board;

public interface Grid extends Board {
    Cell getCell(int x, int y);
}
