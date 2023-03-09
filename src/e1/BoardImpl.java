package e1;

public record BoardImpl(int rows, int columns) implements Board {

    @Override
    public boolean isValidPosition(final int row, final int column) {
        return !(row < 0 || column < 0 || row >= this.rows || column >= this.columns);
    }
}
