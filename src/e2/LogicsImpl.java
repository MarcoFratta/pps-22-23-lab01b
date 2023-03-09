package e2;

import java.util.Optional;

public class LogicsImpl implements Logics {

    public static final int NO_ADJACENT_MINES = 0;
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
    public boolean isWin() {
        return this.state.equals(GameState.WIN);
    }

    @Override
    public boolean hit(final int row, final int column) {
        this.clickedGrid.doAction(row, column);
        this.recursiveCallCheck(row, column);
        return this.minedGrid.check(row, column);
    }

    private void recursiveCallCheck(final int row, final int column) {
        this.getAdjacentMines(row, column).ifPresent(n -> {
            if(n == NO_ADJACENT_MINES){
                this.clickedGrid.getCell(row, column)
                        .getAdjacent().stream().filter(p-> this.clickedGrid.isValidPosition(p.getX(),p.getY()))
                        .filter(p -> !this.clickedGrid.check(p.getX(),p.getY()))
                        .forEach(p -> this.hit(p.getX(),p.getY()));
            }
        });
    }

    @Override
    public boolean hasMine(final int row, final int column) {
        try {
            return this.minedGrid.check(row, column);
        }catch (final Exception ignored){
            return false;
        }
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
    public Optional<Integer> getAdjacentMines(final int row, final int column) {
        return this.clickedGrid.check(row,column) ?
                this.countAdjacentMines(row, column) : Optional.empty();
    }

    private Optional<Integer> countAdjacentMines(final int row, final int column) {
        return Optional.of(Math.toIntExact(this.clickedGrid.getCell(row, column)
                .getAdjacent().stream()
                .filter(p -> this.hasMine(p.getX(), p.getY())).count()));
    }
}
