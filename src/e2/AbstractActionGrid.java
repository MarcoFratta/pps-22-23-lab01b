package e2;

import java.util.stream.Stream;

public abstract class AbstractActionGrid implements ActionGrid{

    protected Grid grid;
    protected AbstractActionGrid(final Grid grid){
        this.grid = grid;
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
        this.checkValidity(row,column);
        this.actionMethod(row,column);
    }
    @Override
    public boolean check(final int row, final int column) {
        this.checkValidity(row,column);
        return this.checkMethod(row,column);
    }
    private void checkValidity(final int row, final int column) {
        if(!this.grid.isValidPosition(row,column)){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Stream<Cell> stream() {
        return this.grid.stream().filter(c -> this.check(c.getRow(),c.getColumn()));
    }

    protected abstract void actionMethod(int row, int column);
    protected abstract boolean checkMethod(int row, int column);
}
