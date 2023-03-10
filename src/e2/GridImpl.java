package e2;

import e1.Board;
import e1.BoardImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class GridImpl implements Grid {

    private final Board board;
    private final Map<Pair<Integer,Integer>, Cell> map;

    public GridImpl(final int row, final int column){
        this.board = new BoardImpl(row,column);
        this.map = new HashMap<>(row*column);
        this.populateGrid();
    }

    private void populateGrid() {
        for (int i = 0; i < this.board.rows(); i++) {
            for (int j = 0; j < this.board.columns(); j++) {
                this.map.put(new Pair<>(i,j), new CellImpl(i,j));
            }
        }
    }
    @Override
    public int rows() {
        return this.board.rows();
    }

    @Override
    public int columns() {
        return this.board.columns();
    }

    @Override
    public boolean isValidPosition(final int x, final int y) {
        return this.board.isValidPosition(x,y);
    }

    @Override
    public Cell getCell(final int x, final int y) {
        if(!this.board.isValidPosition(x,y)) {
            throw new IllegalArgumentException();
        }
        return this.map.get(new Pair<>(x,y));
    }

    @Override
    public Stream<Cell> stream() {
        final Stream.Builder<Cell> stream = Stream.builder();
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.columns(); j++) {
                stream.add(this.getCell(i,j));
            }
        }
        return stream.build();
    }
}
