package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {


    public static final int SIZE = 5;
    private static final int MINES_NUMBERS = 5;
    private Logics logics;

    @BeforeEach
    public void setUp(){
        this.logics = new LogicsImpl(SIZE, MINES_NUMBERS);
    }


    @Test
    void testNotWinAtStart(){
        assertFalse(this.logics.isWin());
    }
    @Test
    void testCanCreateMines(){
        assertEquals(MINES_NUMBERS,
                Utils.countMines(this.logics::hasMine, SIZE));
    }

    @Test
    void testCanHit(){
        this.logics = new LogicsImpl(SIZE, 0);
        final int x = 3;
        final int y = 3;
        assertFalse(this.logics.hit(x,y));
    }

    @Test
    void testCanHitAMine(){
        final int size = 3;
        this.logics = new LogicsImpl(size, size*size);
        assertTrue(this.logics.hit(size-1,size-1));
    }

    @Test
    void testCanFlagAndRemoveFlag() {
        final int row = 3;
        final int column = 2;
        assertFalse(this.logics.hasFlag(row, column));
        this.logics.flag(row, column);
        assertTrue(this.logics.hasFlag(row, column));
        this.logics.flag(row, column);
        assertFalse(this.logics.hasFlag(row, column));
    }

    @Test
    void testSelected(){
        final int row = 3;
        final int column = 2;
        assertFalse(this.logics.isSelected(row,column));
        this.logics.hit(row,column);
        assertTrue(this.logics.isSelected(row,column));
    }


}
