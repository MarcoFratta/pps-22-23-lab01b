package e2;

import java.util.HashSet;
import java.util.Set;

public class SwitchableGrid extends AbstractActionGrid {

    private final Set<Pair<Integer,Integer>> flagged;

    protected SwitchableGrid(final Grid grid) {
        super(grid);
        this.flagged = new HashSet<>();
    }

    @Override
    protected void actionMethod(final int row, final int column) {
        final var p = new Pair<>(row,column);
        if(this.flagged.contains(p)) {
            this.flagged.remove(p);
        } else {
            this.flagged.add(p);
        }
    }

    @Override
    protected boolean checkMethod(final int row, final int column) {
        return this.flagged.contains(new Pair<>(row,column));
    }
}
