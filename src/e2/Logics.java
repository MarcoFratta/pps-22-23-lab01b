package e2;

import java.util.Optional;

public interface Logics {

    boolean isWin();

    boolean hasMine(int row, int column);

    boolean hit(int row, int column);

    void flag(int row, int column);

    boolean hasFlag(int row, int column);

    Optional<Integer> getAdjacentMines(int row, int column);
}
