package e2;

import e1.BoardTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridTest extends BoardTest {

    protected Grid grid;
    @BeforeEach
    void beforeEach(){
        this.grid = new GridImpl(SIZE,SIZE);
        this.board = this.grid;
    }

    @Test
    void testGetCell(){
        final int x = 3;
        final int y = 4;
        assertEquals(new CellImpl(3,4), this.grid.getCell(x,y));
    }

    @Test
    void testForEach(){
        final List<Cell> list = new ArrayList<>();
        this.grid.stream().forEach(list::add);
        assertEquals(SIZE*SIZE, list.size());
    }

}
