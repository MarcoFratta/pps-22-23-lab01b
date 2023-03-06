package e1;

public class BoardImpl implements Board {
    private final int rows;
    private final int columns;

    public BoardImpl(final int rows, final int columns) {

        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public int getRowsNumber() {
        return this.rows;
    }

    @Override
    public int getColumnsNumber() {
        return this.columns;
    }

    @Override
    public boolean isValidPosition(final int row, final int column) {
        return !(row < 0 || column < 0 || row >= this.rows || column >= this.columns);
    }
}
