package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Optional;

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
                Utils.countOnAllGrid(this.logics::hasMine, SIZE));
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
        this.logics = new LogicsImpl(3, 3*3);
        final int row = 1;
        final int column = 1;
        assertEquals(Optional.empty(),this.logics.getAdjacentMines(row,column));
        this.logics.hit(row,column);
        assertEquals(Optional.of(8),this.logics.getAdjacentMines(row,column));
    }

    @Test
    void testRecursiveSelection(){
        this.logics = new LogicsImpl(SIZE,0);
        this.logics.hit(0,0);
        assertEquals(0,Utils.countOnAllGrid((x,y) ->
                this.logics.getAdjacentMines(x,y).equals(Optional.empty()), SIZE));
    }

    @Test
    void testFailOnDoubleClicking(){
        this.logics.hit(0,0);
        assertThrows(IllegalArgumentException.class ,
                () -> this.logics.hit(0,0));
    }

    @Test
    void testCanWinAfterClickingAll() {
        final int size = 5;
        this.logics = new LogicsImpl(size, 1);
        final var minePosition = Utils.find((x, y) -> this.logics.hasMine(x, y), size);
        assert minePosition != null;
        Utils.foreachCellDo((i,j) ->{
        if(!Objects.equals(i, minePosition.getX()) ||
                !Objects.equals(j, minePosition.getY())) {
            try {
                this.logics.hit(i, j);
            }catch (final Exception ignored){}
        }},size);
        assertTrue(this.logics.isWin());
    }

    @Test
    void testNotWinAfterAMine(){
        final var minePosition = Utils.find((x, y) -> this.logics.hasMine(x, y), SIZE);
        assert minePosition != null;
        this.logics.hit(minePosition.getX(), minePosition.getY());
        assertFalse(this.logics.isWin());
    }
}
