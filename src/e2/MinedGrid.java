package e2;

import java.util.HashSet;
import java.util.Set;

public class MinedGrid implements ActionGrid {

    private final Grid grid;
    private final Set<Pair<Integer,Integer>> clickedSet;

    public MinedGrid(final Grid grid) {
        this.grid = grid;
        this.clickedSet = new HashSet<>();
    }

    @Override
    public int rows() {
        return this.grid.rows();
    }

    @Override
    public int columns() {
        return this.grid.columns();
    }

    @Override
    public boolean isValidPosition(final int x, final int y) {
        return this.grid.isValidPosition(x,y);
    }

    @Override
    public Cell getCell(final int x, final int y) {
        return this.grid.getCell(x,y);
    }

    @Override
    public void doAction(final int row, final int column) {
        if(!this.grid.isValidPosition(row,column)){
            throw new IllegalArgumentException();
        }
        this.clickedSet.add(new Pair<>(row,column));
    }

    @Override
    public boolean check(final int row, final int column) {
        if(!this.grid.isValidPosition(row,column)){
            throw new IllegalArgumentException();
        }
        return this.clickedSet.contains(new Pair<>(row,column));
    }

    @Override
    public void undoAction(final int row, final int column) {
        final var p = new Pair<>(row, column);
        if(!this.grid.isValidPosition(row,column) ||
        !this.clickedSet.contains(p)){
            throw new IllegalArgumentException();
        }
        this.clickedSet.remove(p);
    }
}
