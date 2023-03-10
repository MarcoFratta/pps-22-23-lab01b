package e2;

import java.util.HashSet;
import java.util.Set;

public class SelectableGrid extends AbstractActionGrid {

    private final Set<Pair<Integer,Integer>> clickedSet;



    public SelectableGrid(final Grid grid) {
        super(grid);
        this.clickedSet = new HashSet<>();
    }

    @Override
    protected void actionMethod(final int row, final int column) {
        this.clickedSet.add(new Pair<>(row,column));
    }

    @Override
    protected boolean checkMethod(final int row, final int column) {
        return this.clickedSet.contains(new Pair<>(row,column));
    }
}
