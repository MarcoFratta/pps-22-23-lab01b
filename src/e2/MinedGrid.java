package e2;

import java.util.HashSet;
import java.util.Set;

public class MinedGrid extends AbstractActionGrid {

    private final Set<Pair<Integer,Integer>> clickedSet;

    public MinedGrid(final Grid grid) {
        super(grid);
        this.clickedSet = new HashSet<>();
    }

    @Override
    public void actionMethod(final int row, final int column) {
        this.clickedSet.add(new Pair<>(row,column));
    }

    @Override
    public boolean checkMethod(final int row, final int column) {
        return this.clickedSet.contains(new Pair<>(row,column));
    }

    @Override
    public void undoMethod(final int row, final int column) {
        final var p = new Pair<>(row, column);
        if(!this.clickedSet.contains(p)){
            throw new IllegalArgumentException();
        }
        this.clickedSet.remove(p);
    }
}
