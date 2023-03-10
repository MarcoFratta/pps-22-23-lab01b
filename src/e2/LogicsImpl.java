package e2;

import java.util.Optional;

public class LogicsImpl implements Logics {

    private static final int NO_ADJACENT_MINES = 0;
    private final ActionGrid minedGrid;
    private final ActionGrid flagGrid;
    private final ActionGrid clickedGrid;
    private final int numberOfCellToWin;

    public LogicsImpl(final int size, final int mines) {
        final Grid grid = new GridImpl(size, size);
        this.minedGrid = new RandomGrid(new SelectableGrid(grid), mines);
        this.clickedGrid = new SelectableGrid(grid);
        this.flagGrid = new FlagGrid(grid);
        this.numberOfCellToWin = size * size - mines;
    }

    @Override
    public boolean isWin() {
        return this.clickedGrid.stream().count() == this.numberOfCellToWin &&
                this.clickedGrid.stream()
                .noneMatch(cell -> this.minedGrid.check(cell.getRow(), cell.getColumn()));
    }

    @Override
    public boolean hit(final int row, final int column) {
        if(this.clickedGrid.check(row,column)){
            throw new IllegalArgumentException();
        }
        this.clickedGrid.doAction(row, column);
        this.recursiveCallCheck(row, column);
        return this.minedGrid.check(row, column);
    }

    @Override
    public boolean hasMine(final int row, final int column) {
        return this.minedGrid.check(row, column);
    }

    @Override
    public void flag(final int row, final int column) {
        this.flagGrid.doAction(row,column);
    }

    @Override
    public boolean hasFlag(final int row, final int column) {
        return this.flagGrid.check(row,column);
    }

    @Override
    public Optional<Integer> getAdjacentMines(final int row, final int column) {
        return this.clickedGrid.check(row,column) ? this.countAdjacentMines(row, column) : Optional.empty();
    }

    private Optional<Integer> countAdjacentMines(final int row, final int column) {
        return Optional.of((int) this.clickedGrid.getCell(row, column).getAdjacent()
                .stream().filter(p -> this.hasMine(p.getX(), p.getY())).count());
    }

    private void recursiveCallCheck(final int row, final int column) {
        this.getAdjacentMines(row, column).ifPresent(minesNumber -> {
            if(minesNumber == NO_ADJACENT_MINES){
                this.clickedGrid.getCell(row, column).getAdjacent().stream()
                        .filter(p -> !this.clickedGrid.check(p.getX(),p.getY()))
                        .forEach(p -> this.hit(p.getX(),p.getY()));
            }
        });
    }
}
