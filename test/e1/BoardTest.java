package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    public static final int SIZE = 5;
    protected Board board;


    @BeforeEach
    void beforeEach() {
        this.board = new BoardImpl(SIZE,SIZE);
    }

    @Test
    void testBoardRowsSize(){
        assertEquals(SIZE, this.board.rows());
    }

    @Test
    void testBoardColumnsSize(){
        final int columns = 6;
        this.board = new BoardImpl(SIZE, columns);
        assertEquals(columns, this.board.columns());
    }

    @Test
    void testValidPositions(){
        assertFalse(this.board.isValidPosition(-1,-1));
        assertTrue(this.board.isValidPosition(2,2));
        assertFalse(this.board.isValidPosition(SIZE, SIZE + 1));
    }
}
