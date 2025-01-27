package e2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CellImpl implements Cell {
    private final int x;
    private final int y;

    private final Set<Pair<Integer,Integer>> adjacent;

    public CellImpl(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.adjacent = this.getAdjacentCells();
    }
    @Override
    public int getRow() {
        return this.x;
    }

    @Override
    public int getColumn() {
        return this.y;
    }

    @Override
    public Set<Pair<Integer, Integer>> getAdjacent() {
        return Set.copyOf(this.adjacent);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof CellImpl cell)) return false;
        return this.x == cell.x && this.y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public String toString() {
        return "CellImpl{" +
                "x=" + this.x +
                ", y=" + this.y +
                '}';
    }

    private Set<Pair<Integer, Integer>> getAdjacentCells() {
        final Set<Pair<Integer,Integer>> set = new HashSet<>();
        for(int i = -1; i<= 1; i++){
            for (int j = -1; j <=1; j++) {
                set.add(new Pair<>(this.x + i, this.y + j));
            }
        }
        set.remove(new Pair<>(this.x, this.y));
        return set;
    }


}
