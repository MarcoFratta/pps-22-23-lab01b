package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomGridTest extends SelectableGridTest {


    private static final int MINES = 5;

    @BeforeEach
    void beforeEach(){
        super.beforeEach();
        super.actionGrid = new RandomGrid(this.actionGrid, MINES);
    }

    @Test
    void testRandomInit(){
        assertEquals(MINES, Utils.countChecked(this.actionGrid));
    }

    @Test
    void testFailTooManyMines(){
        assertThrows(IllegalArgumentException.class,
                () ->  super.actionGrid = new RandomGrid(this.actionGrid, 1 + SIZE * SIZE));
    }

    @Test
    void testFailNegativeMines(){
        assertThrows(IllegalArgumentException.class,
                () ->  super.actionGrid = new RandomGrid(this.actionGrid, -1));
    }


}
