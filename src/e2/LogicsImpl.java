package e2;

import e1.Board;
import e1.BoardImpl;

public class LogicsImpl implements Logics {

    private final Board board;

    public LogicsImpl(final int size, final int mines) {
        this.board = new BoardImpl(size,size);
    }

    @Override
    public boolean hasMine(final int i, final int j) {
        return false;
    }
}
