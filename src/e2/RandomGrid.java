package e2;

import java.util.Random;

public class RandomGrid extends AbstractActionGrid{

    private final ActionGrid actionGrid;
    protected RandomGrid(final ActionGrid grid, final int mines) {
        super(grid);
        this.actionGrid = grid;
        this.init(mines);
    }

    private void init(final int mines) {
        if(mines > this.grid.rows() * this.grid.columns() || mines < 0){
            throw new IllegalArgumentException();
        }
        final var r = new Random();
        for (int i = 0; i < mines ; i++) {
            final int x = r.nextInt(this.grid.rows());
            final int y = r.nextInt(this.grid.columns());
            if(this.actionGrid.check(x,y)){
                i--;
            } else {
                this.actionGrid.doAction(x,y);
            }
        }
    }

    @Override
    protected void actionMethod(final int row, final int column) {
        this.actionGrid.doAction(row,column);
    }

    @Override
    protected boolean checkMethod(final int row, final int column) {
        return this.actionGrid.check(row,column);
    }
}
