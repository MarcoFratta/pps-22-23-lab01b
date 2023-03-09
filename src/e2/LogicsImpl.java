package e2;

public class LogicsImpl implements Logics {

    private final GameState state;
    private final ActionGrid minedGrid;
    private final ActionGrid flaggedGrid;
    private final ActionGrid clickedGrid;

    public LogicsImpl(final int size, final int mines) {
        final Grid grid = new GridImpl(size, size);
        this.minedGrid = new RandomGrid(new SelectableGrid(grid), mines);
        this.clickedGrid = new SelectableGrid(grid);
        this.flaggedGrid = new SelectableGrid(grid);
        this.state = GameState.PLAYING;
    }

    @Override
    public boolean hasMine(final int row, final int column) {
        return this.minedGrid.check(row,column);
    }

    @Override
    public boolean isWin() {
        return this.state.equals(GameState.WIN);
    }

    @Override
    public boolean hit(final int row, final int column) {
        this.clickedGrid.doAction(row, column);
        return this.minedGrid.check(row, column);
    }

    @Override
    public void flag(final int row, final int column) {
        if(this.hasFlag(row,column)) {
            this.flaggedGrid.undoAction(row, column);
        } else {
            this.flaggedGrid.doAction(row, column);
        }
    }

    @Override
    public boolean hasFlag(final int row, final int column) {
        return this.flaggedGrid.check(row,column);
    }

    @Override
    public boolean isSelected(final int row, final int column) {
        return this.clickedGrid.check(row,column);
    }
}
