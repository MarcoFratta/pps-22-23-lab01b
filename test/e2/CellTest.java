package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CellTest {


    private static final int X = 4;
    private static final int Y = 3;
    private Cell cell;


    @BeforeEach
    void setUp(){
        this.cell = new CellImpl(X,Y);
    }

    @Test
    void testGetXPosition(){
        assertEquals(X,this.cell.getRow());
    }

    @Test
    void testGetYPosition(){
        assertEquals(Y,
                this.cell.getColumn());
    }

    @Test
    void testAdjacentCells(){
        assertTrue(this.cell.getAdjacent()
                .contains(new Pair<>(X+1,Y+1)));
    }
    @Test
    void testAllAdjacentCells(){
        assertEquals(Set.of(
                new Pair<>(X - 1,Y - 1),
                new Pair<>(X - 1,Y),
                new Pair<>(X - 1,Y + 1),
                new Pair<>(X,Y - 1),
                new Pair<>(X,Y + 1),
                new Pair<>(X + 1,Y - 1),
                new Pair<>(X + 1,Y),
                new Pair<>(X + 1,Y + 1)),
                this.cell.getAdjacent());
    }
}
