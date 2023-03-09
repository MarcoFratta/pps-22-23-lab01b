package e2;

import java.util.Set;

public interface Cell {
    int getRow();

    int getColumn();

    Set<Pair<Integer,Integer>> getAdjacent();
}
